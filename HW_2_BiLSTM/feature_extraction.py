## If I weren't so dumb, I would have put this in a dictionary and do a for loop
def extractSuffix(orthotoken):
        # Check suffixes
        if orthotoken.endswith('able'):
                return 'able'
        elif orthotoken.endswith('ac'):
                return 'ac'
        elif orthotoken.endswith('acity'):
                return 'acity'
        elif orthotoken.endswith('ocity'):
                return 'ocity'
        elif orthotoken.endswith('ade'):
                return 'ade'
        elif orthotoken.endswith('age'):
                return 'age'
        elif orthotoken.endswith('aholic'):
                return 'aholic'
        elif orthotoken.endswith('oholic'):
                return 'oholic'
        elif orthotoken.endswith('al'):
                return 'al'
        elif orthotoken.endswith('algia'):
                return 'algia'
        elif orthotoken.endswith('an'):
                return 'an'
        elif orthotoken.endswith('ance'):
                return 'ance'
        elif orthotoken.endswith('ant'):
                return 'ant'
        elif orthotoken.endswith('ar'):
                return 'ar'
        elif orthotoken.endswith('ard'):
                return 'ard'
        elif orthotoken.endswith('arian'):
                return 'arian'
        elif orthotoken.endswith('arium'):
                return 'arium'
        elif orthotoken.endswith('ary'):
                return 'ary'
        elif orthotoken.endswith('ate'):
                return 'ate'
        elif orthotoken.endswith('ation'):
                return 'ation'
        elif orthotoken.endswith('ative'):
                return 'ative'
        elif orthotoken.endswith('cide'):
                return 'cide'
        elif orthotoken.endswith('cracy'):
                return 'cracy'
        elif orthotoken.endswith('crat'):
                return 'crat'
        elif orthotoken.endswith('cule'):
                return 'cule'
        elif orthotoken.endswith('cy'):
                return 'cy'
        elif orthotoken.endswith('cycle'):
                return 'cycle'
        elif orthotoken.endswith('dom'):
                return 'dom'
        elif orthotoken.endswith('dox'):
                return 'dox'
        elif orthotoken.endswith('ectomy'):
                return 'ectomy'
        elif orthotoken.endswith('ed'):
                return 'ed'
        elif orthotoken.endswith('ee'):
                return 'ee'
        elif orthotoken.endswith('eer'):
                return 'eer'
        elif orthotoken.endswith('emia'):
                return 'emia'
        elif orthotoken.endswith('en'):
                return 'en'
        elif orthotoken.endswith('ence'):
                return 'ence'
        elif orthotoken.endswith('ency'):
                return 'ency'
        elif orthotoken.endswith('ent'):
                return 'ent'
        elif orthotoken.endswith('er'):
                return 'er'
        elif orthotoken.endswith('ern'):
                return 'ern'
        elif orthotoken.endswith('escence'):
                return 'escence'
        elif orthotoken.endswith('ese'):
                return 'ese'
        elif orthotoken.endswith('esque'):
                return 'esque'
        elif orthotoken.endswith('ess'):
                return 'ess'
        elif orthotoken.endswith('est'):
                return 'est'
        elif orthotoken.endswith('etic'):
                return 'etic'
        elif orthotoken.endswith('ette'):
                return 'ette'
        elif orthotoken.endswith('ful'):
                return 'ful'
        elif orthotoken.endswith('fy'):
                return 'fy'
        elif orthotoken.endswith('gamy'):
                return 'gamy'
        elif orthotoken.endswith('gon'):
                return 'gon'
        elif orthotoken.endswith('hood'):
                return 'hood'
        elif orthotoken.endswith('ial'):
                return 'ial'
        elif orthotoken.endswith('ian'):
                return 'ian'
        elif orthotoken.endswith('iasis'):
                return 'iasis'
        elif orthotoken.endswith('iatric'):
                return 'iatric'
        elif orthotoken.endswith('ible'):
                return 'ible'
        elif orthotoken.endswith('ic'):
                return 'ic'
        elif orthotoken.endswith('ical'):
                return 'ical'
        elif orthotoken.endswith('ile'):
                return 'ile'
        elif orthotoken.endswith('ily'):
                return 'ily'
        elif orthotoken.endswith('ine'):
                return 'ine'
        elif orthotoken.endswith('ing'):
                return 'ing'
        elif orthotoken.endswith('ion'):
                return 'ion'
        elif orthotoken.endswith('ious'):
                return 'ious'
        elif orthotoken.endswith('ish'):
                return 'ish'
        elif orthotoken.endswith('ism'):
                return 'ism'
        elif orthotoken.endswith('ist'):
                return 'ist'
        elif orthotoken.endswith('ite'):
                return 'ite'
        elif orthotoken.endswith('itis'):
                return 'itis'
        elif orthotoken.endswith('ity'):
                return 'ity'
        elif orthotoken.endswith('ive'):
                return 'ive'
        elif orthotoken.endswith('ization'):
                return 'ization'
        elif orthotoken.endswith('ize'):
                return 'ize'
        elif orthotoken.endswith('less'):
                return 'less'
        elif orthotoken.endswith('let'):
                return 'let'
        elif orthotoken.endswith('like'):
                return 'like'
        elif orthotoken.endswith('ling'):
                return 'ling'
        elif orthotoken.endswith('log'):
                return 'log'
        elif orthotoken.endswith('ly'):
                return 'ly'
        elif orthotoken.endswith('ment'):
                return 'ment'
        elif orthotoken.endswith('ness'):
                return 'ness'
        elif orthotoken.endswith('oid'):
                return 'oid'
        elif orthotoken.endswith('ology'):
                return 'ology'
        elif orthotoken.endswith('oma'):
                return 'oma'
        elif orthotoken.endswith('onym'):
                return 'onym'
        elif orthotoken.endswith('opia'):
                return 'opia'
        elif orthotoken.endswith('opsy'):
                return 'opsy'
        elif orthotoken.endswith('or'):
                return 'or'
        elif orthotoken.endswith('ory'):
                return 'ory'
        elif orthotoken.endswith('osis'):
                return 'osis'
        elif orthotoken.endswith('otomy'):
                return 'otomy'
        elif orthotoken.endswith('ostomy'):
                return 'ostomy'
        elif orthotoken.endswith('ous'):
                return 'ous'
        elif orthotoken.endswith('path'):
                return 'path'
        elif orthotoken.endswith('pathy'):
                return 'pathy'
        elif orthotoken.endswith('phile'):
                return 'phile'
        elif orthotoken.endswith('phobia'):
                return 'phobia'
        elif orthotoken.endswith('phone'):
                return 'phone'
        elif orthotoken.endswith('phyte'):
                return 'phyte'
        elif orthotoken.endswith('plegia'):
                return 'plegia'
        elif orthotoken.endswith('scope'):
                return 'scope'
        elif orthotoken.endswith('scopy'):
                return 'scopy'
        elif orthotoken.endswith('s'):
                return 's'
        elif orthotoken.endswith('scribe'):
                return 'scribe'
        elif orthotoken.endswith('script'):
                return 'script'
        elif orthotoken.endswith('sect'):
                return 'sect'
        elif orthotoken.endswith('ship'):
                return 'ship'
        elif orthotoken.endswith('sion'):
                return 'sion'
        elif orthotoken.endswith('some'):
                return 'some'
        elif orthotoken.endswith('sophy'):
                return 'sophy'
        elif orthotoken.endswith('sophic'):
                return 'sophic'
        elif orthotoken.endswith('th'):
                return 'th'
        elif orthotoken.endswith('tion'):
                return 'tion'
        elif orthotoken.endswith('tome'):
                return 'tome'
        elif orthotoken.endswith('tomy'):
                return 'tomy'
        elif orthotoken.endswith('trophy'):
                return 'trophy'
        elif orthotoken.endswith('tude'):
                return 'tude'
        elif orthotoken.endswith('ty'):
                return 'ty'
        elif orthotoken.endswith('ular'):
                return 'ular'
        elif orthotoken.endswith('uous'):
                return 'uous'
        elif orthotoken.endswith('ure'):
                return 'ure'
        elif orthotoken.endswith('ward'):
                return 'ward'
        elif orthotoken.endswith('ware'):
                return 'ware'
        elif orthotoken.endswith('wise'):
                return 'wise'
        elif orthotoken.endswith('ly'):
                return 'ly'
        elif orthotoken.endswith('y'):
                return 'y'
        else:
                return ''

## Checks if word is a number
def isNumber(orthotoken):
        try:
                float(s)
                return True
        except ValueError:
                pass
return False


## Checks if word is capitalized
def isCapitalized(orthotoken):
    return orthotoken[0].isupper()

def extractHyphen(orthotoken):
        if orthotoken.find('-')!=-1:
                return True
        else:
                return False

## Orthographic feature extractor
def extractOrthographicFeatures(orthotoken):
        capitalized = isCapitalized(orthotoken) #returns boolean true if capital

        number = isNumber(orthotoken) #returns boolean true if string is a number

        orthotoken = orthotoken.lower()
        
        suffix = extractSuffix(orthotoken) #will provide suffix string. empty string if no suffix detected

        hyphenated = extractHyphen(orthotoken)
        
        features = []
        features.append(capitalized)
        features.append(number)
        features.append(suffix)
        features.append(hyphenated)

        return features 
