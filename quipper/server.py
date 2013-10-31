import os
from bottle import route, run, request, HTTPResponse
import json
from random import choice

from qparser import parse, deparse
from quipper import Quipper
from settings import HOST, PORT

MEDIA_DIR = 'media/'

@route("/str_to_circuit", method="GET")
def str_to_circuit():
    try:
        source_codes = json.loads(request.GET.get('source_codes', ''))
        return json.dumps(
            [parse(source_code).get_structure() for source_code in source_codes] )
    except Exception as e:
        return HTTPResponse(status=500, body=str(e))

@route("/circuit_to_str", method="GET")
def circuit_to_str():
    try:
        circuits = json.loads(request.GET.get('circuit', ''))
        return json.dumps(
            [deparse(circ) for circ in circuits])
    except Exception as e:
        return HTTPResponse(status=500, body=str(e)) 

@route("/circuit_to_file", method="GET")
def circuit_to_file():
    try:
        name = ''.join(
            choice('abcdef0123456789')
            for _ in range(3))
        name = MEDIA_DIR + name + '.' + request.GET.get('extension', '')
        circuit = request.GET.get('circuit', '')
        q = Quipper(name=None, feed=json.loads(circuit))
        q.get_figure().savefig(name, bbox_inches='tight')
        with open(name, 'rb') as inp:
            data = inp.read()
        os.remove(name)
        return data
    except Exception as e:
        return HTTPResponse(status=500, body=str(e)) 


@route("/str_to_file", method="GET")
def str_to_file():
    try:
        name = ''.join(
            choice('abcdef0123456789')
            for _ in range(3))
        name = MEDIA_DIR + name + '.' + request.GET.get('extension', '')
        source_code = request.GET.get('source_code', '')
        parse(source_code).get_figure().savefig(name, bbox_inches='tight')
        with open(name, 'rb') as inp:
            data = inp.read()
        os.remove(name)
        return data
    except Exception as e:
        return HTTPResponse(status=500, body=str(e))


run(host=HOST, port=PORT)