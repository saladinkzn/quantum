from sympy import Add, I, Integer, Matrix, Mul, Pow, sqrt, Tuple
from sympy.physics.quantum.gate import Gate


class MultiQubitGate(Gate):
    """
    Класс гейтов общего вида, но с ограничением, что число входящих 
    кубитов равно числу исходящих
    """

    def plot_gate(self, circ_plot, gate_idx):
        for wire in self.targets:
            circ_plot.one_qubit_box(
                self.gate_name_plot,
                gate_idx, int(wire)
            )
        circ_plot.control_line(
            gate_idx,
            min(self.targets, key=lambda x: int(x)),
            max(self.targets, key=lambda x: int(x)))
