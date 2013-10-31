function makeFuncBase(){
  var f = {};

  f.selectedQBit  = -1;

  f.makeElem = function(){
    var e = {};
    e.name     = "";
    e.selected = false;
    e.argId    = -1;

    return e;
    }

  f.mkColumn = function(){
    var c = new Array(0);
    if( c.length>f.qBitsCount )
      c.length = f.qBitsCount;

    while( c.length<f.qBitsCount )
      c[c.length] = f.makeElem();

    return c;
    }

  f.onFunctionDelete = function( name ){
    for( var i=0; i<f.gates.length; ++i ){
      if( f.gates[i]===name ){
        f.gates[i] = "";
        for( var r=0; r<f.columns[i].length; ++r ){
          f.columns[i][r].selected = false;
          f.columns[i][r].name     = "";
          f.columns[i][r].argId    = -1;
          }
        }
      }
    }

  f.setQBitCount = function( count ){
    f.qBitsCount = count;

    for( var i=0; i<f.columns.length; ++i ){
      if( f.columns[i]===undefined )
        f.columns[i] = f.mkColumn();

      if( f.columns[i].length>f.qBitsCount )
        f.columns[i].length = f.qBitsCount;

      while( f.columns[i].length<f.qBitsCount )
        f.columns[i][f.columns[i].length] = f.makeElem();
      }

    while( f.qBitNames.length < count )
      f.qBitNames[ f.qBitNames.length ] = ( "Q"+ (f.qBitNames.length+1) );

    f.qBitNames.length = count;
    }

  f.insertColumn = function( pos ){
    f.gates.  splice( pos, 0, "" );
    f.columns.splice( pos, 0, f.mkColumn() );
    }

  f.eraseColumn = function( pos ){
    f.gates.  splice( pos, 1 );
    f.columns.splice( pos, 1 );
    }

  f.setQBitName = function( fn, id, name ){
    if( f===fn )
      f.qBitNames[id] = name;

    for( var i=0; i<f.columns.length; ++i ){
      if( f.gates[i]===fn.name ){
        var c = f.columns[i];
        for( var r=0; r<c.length; ++r )
          if( c[r].argId==id )
            c[r].name = name;
        }
      }
    }

  f.toJSON = function(){
    var fn = {};

    fn.name = f.name;

    fn.labels = new Array(f.qBitsCount);
    for( var i=0; i<fn.labels.length; ++i )
      fn.labels[i] = f.qBitNames[i];//"Q"+(i+1);

    var id = 0;
    fn.circuit = [];

    var spaceEnable = false;

    for( var i=0; i<f.columns.length; ++i ){
      if( f.gates[i]!=="" || spaceEnable ){
        var elt = [];
        elt[0] = f.gates[i];

        if( f.gates[i]!=="" ){
          for( var r=0; r<f.columns[i].length; ++r )
            if( f.columns[i][r].argId!==-1 )
              elt[ f.columns[i][r].argId+1 ] = r;
          }

        fn.circuit[id] = elt;
        ++id;
        }
      }

    return fn;
    }

  return f;
  }

function makeFuncFromJSON( obj ){
  var f = makeFuncBase();
  f.gates      = new Array( obj.circuit.length );
  f.columns    = new Array( obj.circuit.length );

  f.name       = obj.name;
  f.qBitsCount = obj.labels.length;
  f.qBitNames  = new Array( f.qBitsCount );

  f.editColumn    = -1;
  f.eArgsCount    =  0;

  for( var i=0; i<obj.labels.length; ++i )
    f.qBitNames[i] = obj.labels[i];

  for( var i=0; i<obj.circuit.length; ++i ){
    var gt = [];

    for( var r=0; r<f.qBitsCount; ++r ){
      var e = { name:"", selected:false, argId:-1  };
      gt[r] = e;
      }

    f.gates[i] = obj.circuit[i][0];

    for( var r=1; r<obj.circuit[i].length; ++r ){
      var id = obj.circuit[i][r];
      gt[ id ].argId = r-1;//obj.circuit[i][r];
      gt[ id ].name  = "Q"+(gt[id].argId+1);
      gt[ id ].selected = true;
      }

    f.columns[i] = gt;
    }

  return f;
  }

function makeFunc( argN ){
  if( argN===undefined )
    argN = 4;

  var f = makeFuncBase();
  f.gates      = new Array(0);
  f.columns    = new Array(0);
  f.name       = "main";
  f.qBitsCount = 0;
  f.qBitNames  = new Array(0);

  f.editColumn   = -1;
  f.eArgsCount   =  0;

  f.setQBitCount(argN);
  for( var i=0; i<10; ++i )
    f.insertColumn(0);

  return f;
  }
