from sympy.physics.quantum.qasm import Qasm
from sympy.physics.quantum.gate import H, CNOT, X, Z, CGate, CGateS, SWAP, S, T,CPHASE
from sympy.physics.quantum.circuitplot import Mz

from gates import MultiQubitGate


GATES = {
    'H': H,
    # 'h': H,#
    'hadamard': H,#
    # 'CNOT': CNOT,
    # 'cnot': CNOT, #
    'qnot': CNOT,
    # 'swap' :SWAP,#
    # 'SWAP': SWAP,
    # 'x': X,#
    # 'X': X,
    'not': X,
}
def get_index(target, labels):
    """Get qubit labels from the rest of the line,and return indices
    """
    if target not in labels:
        labels.insert(0, target)
    nq = len(labels)
    return flip_index(labels.index(target), nq)


def get_indices(targets, labels):
    return [get_index(t, labels) for t in targets]


def flip_index(i, n):
    """Reorder qubit indices from largest to smallest.
    """
    return n-i-1

def prod(c):
    p = 1
    for ci in c:
        p *= ci
    return p

def get_gates_deep(quipper_function, labels):
    for gate in reversed(quipper_function.circuit):
        if isinstance(gate, MultiQubitGate):
            for g in get_gates_deep(quipper_function.defs[gate.gate_name], labels=gate.label):
                yield(g)
        else:
            _gate = gate.__class__(*[labels[i] for i in gate.label])
            yield(_gate)


class Quipper(object):
    defs = {}

    def __init__(self, *args, name, feed=None, **kwargs):
        self.name = name
        self.circuit = []
        self.labels = []
        self.inits = {}
        self.kwargs = kwargs
        if feed is not None:
            for gate in feed['circuit']:
                gate_name = gate.pop(0)
                if gate_name in self.defs:
                    function = self.defs[gate_name]
                    q = MultiQubitGate(*self.indices(gate))
                    q.gate_name = gate_name
                    q.gate_name_latex = function.name
                    self.circuit.append(q)
                elif gate_name in GATES:
                    self.circuit.append(GATES[gate_name](*self.indices(gate)))
                else:
                    q = MultiQubitGate(*self.indices(gate))
                    q.gate_name = gate_name
                    q.gate_name_latex = gate_name
                    self.circuit.append(q)

            self.labels = list(reversed(feed['labels']))


    def get_circuit(self):
        return prod(reversed(self.circuit))

    def get_figure(self):
        from sympy.physics.quantum.circuitplot import CircuitPlot
        circuit, labels = self.get_circuit(), self.get_labels()
        circ = CircuitPlot(circuit, len(labels), labels=labels, inits=self.inits)
        return circ._figure

    def get_labels(self):
        return list(reversed(self.labels))

    def plot(self):
        from sympy.physics.quantum.circuitplot import CircuitPlot
        circuit, labels = self.get_circuit(), self.get_labels()
        CircuitPlot(circuit, len(labels), labels=labels, inits=self.inits)

    def add(self, command_name, *args):
        if '"controlled"' in args:
            args = filter(lambda x: "controlled" not in x, args)
        function = self.defs.get(command_name)
        if function:
            if len(function.labels) != len(args):
                raise TypeError("%s() must have %d arguments!"
                    % (function.name, len(function.labels)))
            q = MultiQubitGate(*self.indices(args))
            q.gate_name = command_name
            q.gate_name_latex = function.name
            self.circuit.append(q)

        elif command_name in GATES:
            self.circuit.append(GATES[command_name](*self.indices(args)))
        else:
            q = MultiQubitGate(*self.indices(args))
            q.gate_name = command_name
            q.gate_name_latex = command_name
            self.circuit.append(q)

    def indices(self, args):
        return get_indices(args, self.labels)

    def index(self, arg):
        return get_index(arg, self.labels)

    def get_circuit_deep(self):
        p = 1
        for ci in get_gates_deep(self, labels=tuple(range(len(self.labels)))):
            p *= ci
        return p

    def plot(self):
        from sympy.physics.quantum.circuitplot import CircuitPlot
        circuit, labels = self.get_circuit(), self.get_labels()
        circ = CircuitPlot(circuit, len(labels), labels=labels, inits=self.inits)
    
    def plot_deep(self):
        from sympy.physics.quantum.circuitplot import CircuitPlot
        circuit, labels = self.get_circuit_deep(), self.get_labels()
        circ = CircuitPlot(circuit, len(labels), labels=labels, inits=self.inits)

    def get_structure(self, deep=False):
        res = {}
        res['labels'] = list(reversed(self.labels))
        res['name'] = self.name
        circ = []
        circuit = get_gates_deep(self, labels=tuple(range(len(self.labels)))) if deep else self.circuit
        for gate in circuit:
            g = [gate.gate_name]
            for label in gate.label:
                g.append(int(label))
            circ.append(g)
        res['circuit'] = circ
        return res


