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
    name = alphabet['name']
    args = ' '.join(alphabet['labels'])
    res = "{0} :: ({1}) -> ({1})\n{0} ({1}) =do \n".format(name, args)
    # res = alphabet['name']+' :: ('
    # for label in alphabet['labels']:
    #     res += label+' '
    # res += ') -> ('
    # for label in alphabet['labels']:
    #     res += label+' '
    # res += ')\n'
    # res += alphabet['name']+' ('
    # for label in alphabet['labels']:
    #     res += label+' '
    # res += ') =do \n'
    return res

def function(operator, labels):
    args = ' '.join([labels[i] for i in operator[1:]])
    op = gates[operator[0]] if operator[0] in gates else operator[0]
    res = '    {0} <- {1} {0}'.format( args, op)
    # res += ' '.join(args)
    # res += ' <- '
    # res += (gates[operator[0]] if operator[0] in gates else operator[0]) + ' '
    # res += ' '.join(args)
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
