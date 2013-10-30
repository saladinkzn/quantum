import re

def is_a_function(line):
    result = {}
    res = re.match(
    r'(^[a-zA-Z][0-9a-zA-Z]*)([ ]*)([(a-zA-Z][0-9a-zA-Z) ]*)(=do[ ]*$)'
    ,line)
    if res:
        res= res.groups()
        result['name'] = res[0]
        result['return'] = res[2]
    return result

def is_an_operation(line):
    result = {}
    res = re.match(
    r'^([ ]*)([a-zA-Z][0-9a-zA-Z ]*)[ ]*<[-]+[ ]*([a-zA-Z][0-9a-zA-Z]*)[ ]+([a-zA-Z][0-9a-zA-Z \"\' ]*)'
        ,line)
    if res:
        res = res.groups()
        result['return'] = res[1]
        result['function'] = res[2]
        result['params'] = re.findall(r'[\'\""a-zA-Z][\'\"0-9a-zA-Z]*',res[3])
    return result
