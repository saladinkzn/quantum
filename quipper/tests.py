from quipper import Quipper
from qparser import parse, deparse
# from sympy.physics.quantum.circuitplot import CircuitPlot
# from matplotlib import pyplot
from gates import MultiQubitGate

# q = Quipper(name="example")
# q.add("h", 'x')
# q.add('h', 'y')
# q.add('cnot', 'x', 'y')
# q.add('cnot', 'x', 'z')
# q.add('cnot', 'z', 'y')
# q.add('hadamard', 'z')

# q2 = Quipper(name="F")

# q2.add('h', 'x')
# q2.add('cnot', 'y', 'x')
# q2.add('h', 'z')
# q2.add('example', 'x', 'y', 't')
# q2.add('cnot', 'z', 'y')
# q2.add('swap', 'x', 'y')


# s = q2.get_structure()

# print(deparse(s))

# q3 = Quipper(name="G", feed=s, defs={'example': q})

# q2.plot()
# # q2.plot_deep()
# pyplot.show()

text = """a :: (b a c ) -> (b a c )
a (b a c ) =do 
a <- hadamard a 
a c <- qnot a c 
a <- hadamard b"""

q = parse(text)
print(q.get_structure())
print(deparse(q.get_structure()))