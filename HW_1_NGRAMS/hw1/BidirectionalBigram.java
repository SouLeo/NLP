import java.io.*;
import java.util.*;

public class BidirectionalBigram {
    BigramModel forwardModel;
    BigramModelBackwards backwardModel;

    BidirectionalBigram(){
        forwardModel = new BigramModel();
        backwardModel = new BigramModelBackwards();
    }

    void train(List<List<String>> sentences){
        forwardModel.train(sentences);
        backwardModel.train(sentences);
    }

    double sentenceLogProb(List<String> sentence){
        double[] forwardProbs = forwardModel.sentenceTokenProbs(sentence);
        double[] backwardProbs = backwardModel.sentenceTokenProbs(sentence);
        // Maintain total sentence prob as sum of individual token
        // log probs (since adding logs is same as multiplying probs)
        double sentenceLogProb = 0;
        // Reverse the backwardProbs to more easily compare
        for (int i = 0; i < backwardProbs.length/2; i++){
            double temp = backwardProbs[i];
            backwardProbs[i] = backwardProbs[backwardProbs.length - i -1];
            backwardProbs[backwardProbs.length - i - 1] = temp;
        }
        for (int i = 0; i < forwardProbs.length; i++){
            // average the probs to get interpolated prob
            double logProb = Math.log((forwardProbs[i] + backwardProbs[i])/2);
            sentenceLogProb = sentenceLogProb + logProb;
        }
        sentenceLogProb = sentenceLogProb + Math.log((forwardProbs[forwardProbs.length - 1] + backwardProbs[0]) / 2);
        return sentenceLogProb;
    }

    double sentenceLogProb2(List<String> sentence){
        double[] forwardProbs = forwardModel.sentenceTokenProbs(sentence);
        double[] backwardProbs = backwardModel.sentenceTokenProbs(sentence);
        // Maintain total sentence prob as sum of individual token
        // log probs (since adding logs is same as multiplying probs)
        double sentenceLogProb = 0;
        // Reverse the backwardProbs to more easily compare
        for (int i = 0; i < backwardProbs.length/2; i++){
            double temp = backwardProbs[i];
            backwardProbs[i] = backwardProbs[backwardProbs.length - i -1];
            backwardProbs[backwardProbs.length - i - 1] = temp;
        }
        for (int i = 0; i < forwardProbs.length; i++){
            // average the probs to get interpolated prob
            double logProb = Math.log((forwardProbs[i] + backwardProbs[i])/2);
            sentenceLogProb = sentenceLogProb + logProb;
        }
        return sentenceLogProb;
    }

    /* Taken from BigramModel.java */
    public void test (List<List<String>> sentences) {
        // Compute log probability of sentence to avoid underflow
        double totalLogProb = 0;
        // Keep count of total number of tokens predicted
        double totalNumTokens = 0;
        // Accumulate log prob of all test sentences
        for (List<String> sentence : sentences) {
            // Num of tokens in sentence plus 1 for predicting </S>
            totalNumTokens += sentence.size() + 1;
            // Compute log prob of sentence
            double sentenceLogProb = sentenceLogProb(sentence);
            //	    System.out.println(sentenceLogProb + " : " + sentence);
            // Add to total log prob (since add logs to multiply probs)
            totalLogProb += sentenceLogProb;
        }
        // Given log prob compute perplexity
        double perplexity = Math.exp(-totalLogProb / totalNumTokens);
        System.out.println("Perplexity = " + perplexity );
    }

    /* Taken from BigramModel.java */
    public void test2 (List<List<String>> sentences) {
        double totalLogProb = 0;
        double totalNumTokens = 0;
        for (List<String> sentence : sentences) {
            totalNumTokens += sentence.size();
            double sentenceLogProb = sentenceLogProb2(sentence);
            //	    System.out.println(sentenceLogProb + " : " + sentence);
            totalLogProb += sentenceLogProb;
        }
        double perplexity = Math.exp(-totalLogProb / totalNumTokens);
        System.out.println("Word Perplexity = " + perplexity );
    }

    /* Taken from BigramModel.java */
/*
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
                " (# words = " + BigramModel.wordCount(trainSentences) +
                ") \n# Test Sentences = " + testSentences.size() +
                " (# words = " + BigramModel.wordCount(testSentences) + ")");
        // Create a bigram model and train it.
        BidirectionalBigram model = new BidirectionalBigram();
        System.out.println("Training...");
        model.train(trainSentences);
        // Test on training data using test and test2
        model.test(trainSentences);
        model.test2(trainSentences);
        System.out.println("Testing...");
        // Test on test data using test and test2
        model.test(testSentences);
        model.test2(testSentences);
    } */
}
