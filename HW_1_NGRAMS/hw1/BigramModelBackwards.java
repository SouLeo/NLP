import java.io.*;
import java.util.*;

public class BigramModelBackwards extends BigramModel{
    @Override
    /** Backwards model for accumulating unigram and bigram counts for this sentence */
    public void trainSentence(List<String> sentence) {
        List<String> reversedSentence = new ArrayList<String>(sentence);
        Collections.reverse(reversedSentence);
        // First count an initial start sentence token
        String prevToken = "</S>";
        DoubleValue unigramValue = unigramMap.get("</S>");
        unigramValue.increment();
        tokenCount++;
        // For each token in sentence, accumulate a unigram and bigram count
        for (String token : reversedSentence) {
            unigramValue = unigramMap.get(token);
            // If this is the first time token is seen then count it
            // as an unkown token (<UNK>) to handle out-of-vocabulary
            // items in testing
            if (unigramValue == null) {
                // Store token in unigram map with 0 count to indicate that
                // token has been seen but not counted
                unigramMap.put(token, new DoubleValue());
                token = "<UNK>";
                unigramValue = unigramMap.get(token);
            }
            unigramValue.increment();    // Count unigram
            tokenCount++;               // Count token
            // Make bigram string
            String bigram = bigram(prevToken, token);
            DoubleValue bigramValue = bigramMap.get(bigram);
            if (bigramValue == null) {
                // If previously unseen bigram, then
                // initialize it with a value
                bigramValue = new DoubleValue();
                bigramMap.put(bigram, bigramValue);
            }
            // Count bigram
            bigramValue.increment();
            prevToken = token;
        }
        // Account for end of sentence unigram
        unigramValue = unigramMap.get("<S>");
        unigramValue.increment();
        tokenCount++;
        // Account for end of sentence bigram
        String bigram = bigram(prevToken, "<S>");
        DoubleValue bigramValue = bigramMap.get(bigram);
        if (bigramValue == null) {
            bigramValue = new DoubleValue();
            bigramMap.put(bigram, bigramValue);
        }
        bigramValue.increment();
    }

    @Override
    public double sentenceLogProb(List<String> sentence){
        List<String> reversedSentence = new ArrayList<String>(sentence);
        Collections.reverse(reversedSentence);
        // Set start-sentence as initial token
        String prevToken = "</S>";
        // Maintain total sentence prob as sum of individual token
        // log probs (since adding logs is same as multiplying probs)
        double sentenceLogProb = 0;
        // Check prediction of each token in sentence
        for (String token : reversedSentence) {
            // Retrieve unigram prob
            DoubleValue unigramVal = unigramMap.get(token);
            if (unigramVal == null) {
                // If token not in unigram model, treat as <UNK> token
                token = "<UNK>";
                unigramVal = unigramMap.get(token);
            }
            // Get bigram prob
            String bigram = bigram(prevToken, token);
            DoubleValue bigramVal = bigramMap.get(bigram);
            // Compute log prob of token using interpolated prob of unigram and bigram
            double logProb = Math.log(interpolatedProb(unigramVal, bigramVal));
            // Add token log prob to sentence log prob
            sentenceLogProb += logProb;
            // update previous token and move to next token
            prevToken = token;
        }
        // Check prediction of end of sentence token
        DoubleValue unigramVal = unigramMap.get("<S>");
        String bigram = bigram(prevToken, "<S>");
        DoubleValue bigramVal = bigramMap.get(bigram);
        double logProb = Math.log(interpolatedProb(unigramVal, bigramVal));
        // Update sentence log prob based on prediction of </S>
        sentenceLogProb += logProb;
        return sentenceLogProb;

    }

    @Override
    public double[] sentenceTokenProbs(List<String> sentence){
        List<String> reversedSentence = new ArrayList<String>(sentence);
        Collections.reverse(reversedSentence);
        // Set start-sentence as initial token
        String prevToken = "</S>";
        // Vector for storing token prediction probs
        double[] tokenProbs = new double[sentence.size() + 1];
        // Token counter
        int i = 0;
        // Compute prob of predicting each token in sentence
        for (String token : reversedSentence) {
            DoubleValue unigramVal = unigramMap.get(token);
            if (unigramVal == null) {
                token = "<UNK>";
                unigramVal = unigramMap.get(token);
            }
            String bigram = bigram(prevToken, token);
            DoubleValue bigramVal = bigramMap.get(bigram);
            // Store prediction prob for i'th token
            tokenProbs[i] = interpolatedProb(unigramVal, bigramVal);
            prevToken = token;
            i++;
        }
        // Check prediction of end of sentence
        DoubleValue unigramVal = unigramMap.get("<S>");
        String bigram = bigram(prevToken, "<S>");
        DoubleValue bigramVal = bigramMap.get(bigram);
        // Store end of sentence prediction prob
        tokenProbs[i] = interpolatedProb(unigramVal, bigramVal);
        return tokenProbs;
    }

    @Override
    public double sentenceLogProb2(List<String> sentence){
        List<String> reversedSentence = new ArrayList<String>(sentence);
        Collections.reverse(reversedSentence);
        String prevToken = "</S>";
        double sentenceLogProb = 0;
        for (String token : reversedSentence) {
            DoubleValue unigramVal = unigramMap.get(token);
            if (unigramVal == null) {
                token = "<UNK>";
                unigramVal = unigramMap.get(token);
            }
            String bigram = bigram(prevToken, token);
            DoubleValue bigramVal = bigramMap.get(bigram);
            double logProb = Math.log(interpolatedProb(unigramVal, bigramVal));
            sentenceLogProb += logProb;
            prevToken = token;
        }
        return sentenceLogProb;
    }
    public static void main(String[] args) throws IOException {
        // All but last arg is a file/directory of LDC tagged input data
        File[] files = new File[args.length - 1];
        for (int i = 0; i < files.length; i++)
            files[i] = new File(args[i]);
        // Last arg is the TestFrac
        double testFraction = Double.valueOf(args[args.length -1]);
        // Get list of sentences from the LDC POS tagged input files
        List<List<String>> sentences = 	POSTaggedFile.convertToTokenLists(files);
        int numSentences = sentences.size();
        // Compute number of test sentences based on TestFrac
        int numTest = (int)Math.round(numSentences * testFraction);
        // Take test sentences from end of data
        List<List<String>> testSentences = sentences.subList(numSentences - numTest, numSentences);
        // Take training sentences from start of data
        List<List<String>> trainSentences = sentences.subList(0, numSentences - numTest);
        System.out.println("# Train Sentences = " + trainSentences.size() +
                " (# words = " + wordCount(trainSentences) +
                ") \n# Test Sentences = " + testSentences.size() +
                " (# words = " + wordCount(testSentences) + ")");
        // Create a bigram model and train it.
        BigramModelBackwards model = new BigramModelBackwards();
        System.out.println("Training...");
        model.train(trainSentences);
        // Test on training data using test and test2
        model.test(trainSentences);
        model.test2(trainSentences);
        System.out.println("Testing...");
        // Test on test data using test and test2
        model.test(testSentences);
        model.test2(testSentences);
    }
}


