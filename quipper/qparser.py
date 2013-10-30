from quipper import Quipper, GATES
import regular_expressions as reg

gates = dict(zip([g.gate_name for g in GATES.values()], GATES.keys()))

def parse(text):
    lines = text.splitlines()
    q = None
    for line in lines:
        function = reg.is_a_function(line)
        if function:
            q = Quipper(name = function['name'])
        else:
            operation = reg.is_an_operation(line)
            if operation:
                q.add(operation['function'], *operation['params'])
    return q

def title(alphabet):
    res = alphabet['name']+' :: ('
    for label in alphabet['labels']:
        res += label+' '
    res += ') -> ('
    for label in alphabet['labels']:
        res += label+' '
    res += ')\n'
    res += alphabet['name']+' ('
    for label in alphabet['labels']:
        res += label+' '
    res += ') = do \n'
    return res

def function(operator, labels):
    res = '    '
    j = len(operator) - 1
    i = 1
    while i <= j:
        res += labels[i] + ' '
        i += 1
    res += '<- '
    res += (gates[operator[0]] if operator[0] in gates else operator[0]) + ' '
    i = 1
    while i <= j:
        res += labels[i] + ' '
        i += 1
    return res

def body(alphabet):
    circuit = alphabet['circuit']
    res =''
    for operator in circuit:
        res += function(operator, alphabet['labels'])+'\n'
    return res

def deparse(alphabet):
    res = title(alphabet)
    res += body(alphabet)
    return res
