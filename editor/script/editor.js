function Editor( editor ){
  var spaceX = 50, spaceY = 40, dx = spaceX+30, dy = spaceY;
  var mx = 0, my = 0;

  var triangleImg = new Image();
  triangleImg.src = "../img/triangle.png";
  var triangleAlpha = 0;

  var closeImg = new Image();
  closeImg.src = "../img/close.png";
  var closeImgAlpha = 0;

  var p = painter( editor );
  var mOver = {x:0, y:0};

  var functions = [];
  var functionsNames = [];

  var func = makeFunc();
  functions[func.name] = func;
  functionsNames[0] = func.name;

  function drawQBitButtonRect( id, str ){
    var qsize = 20;
    var r = {};
    r.x = dx-qsize-spaceX;
    r.y = dy+id*spaceY-qsize/2;
    r.w = qsize;
    r.h = qsize;

    return r;
    }

  function drawQBitButton( p, id, str ){
    p.fillStyle = '#FFFFFF';
    var rect = drawQBitButtonRect(id, str);

    if( rect.x < mx && mx < rect.x+rect.w &&
        rect.y < my && my < rect.y+rect.h )
      p.rectangle( rect.x-1, rect.y-1, rect.w+2, rect.h+2 ); else
      p.rectangle( rect.x, rect.y, rect.w, rect.h );
    p.fillStyle = '#000000';

    var sz = p.measureText(str);
    p.fillText(str, rect.x+rect.w/2-sz.width/2, rect.y+rect.h-8 );
    }

  function closeBtnRect( i ){
    var r = { x:dx+i*spaceX-closeImg.width/2,
              y:func.qBitsCount*spaceY+spaceY/2,
              w:closeImg.width,
              h:closeImg.height };
    return r;
    }

  function editorCrideRect( i, r ){
    var re = { x: dx+r*spaceX,
               y: dy+i*spaceY,
               r: 5 };

    return re;
    }

  function eachEditCride( f ){
    for( var i=0; i<func.qBitsCount; ++i )
      for( var r=0; r<func.columns.length; ++r ){
        f(func.columns[r][i], i,r);
        }
    }

  function triangleRect(i){
    var x = {
      x:dx+i*spaceX-spaceX/2-triangleImg.width/2,
      y:dy-spaceY+20,
      w:triangleImg.width,
      h:triangleImg.height
    };

    return x;
    }

  function paintEvent(){
    editor.width  = editor.clientWidth;
    editor.height = editor.clientHeight;

    p.fillStyle = '#FFFFFF';
    p.strokeStyle = '#000000';
    for( var i=0; i<func.qBitsCount; ++i ){
      p.line(10, i*spaceY+spaceY, editor.width-20, i*spaceY+spaceY );
      }

    p.globalAlpha = 0.2;

    for( var i=0; i<func.qBitsCount; ++i ){
      p.globalAlpha = 1;

      if( func.selectedQBit===i )
        p.strokeStyle = '#0000FF'; else
        p.strokeStyle = '#000000';
      drawQBitButton(p, i, func.qBitNames[i]);//"Q"+(i+1));
      }

    p.strokeStyle = '#000000';
    eachEditCride( function( q, i, r ){
      if( (mOver.x-r <=2 && r-mOver.x<=1)|| r===func.editColumn || func.gates[r]!=="" ){
        if( r===mOver.x || r+1===mOver.x || r===func.editColumn || func.gates[r]!=="" )
          p.globalAlpha = 0.5; else
          p.globalAlpha = 0.2;

        var re = editorCrideRect(i,r);
        if( (mx-re.x)*(mx-re.x) + (my-re.y)*(my-re.y)<re.r*re.r )
          re.r++;

        if( func.columns[r][i].selected )
          p.cridef( re.x, re.y, re.r ); else
          p.cride ( re.x, re.y, re.r );

        p.globalAlpha = 1;
        p.fillText( q.name, re.x+re.r, re.y+8 );
        }
      });

    p.globalAlpha = 1;
    for( var i=0; i<func.columns.length; ++i ){
      var str = func.gates[i];
      var sz = p.measureText(str);
      p.fillText(str, dx+spaceX*i-spaceX/2 + (spaceX-sz.width)/2, dy-spaceY/2 );
      }

    p.globalAlpha = 1;
    //drawQBitButton(p, func.qBitsCount, "+");

    for( var i=0; i<=func.columns.length; ++i ){
      if( i===mOver.x )
        p.globalAlpha =   1*triangleAlpha; else
        p.globalAlpha = 0.4*triangleAlpha;

      var re = triangleRect(i);
      p.drawImage(triangleImg, re.x, re.y);
      }

    for( var i=0; i<func.columns.length; ++i )
      if( mOver.x-i <=1 && i-mOver.x<=0 ){
        p.globalAlpha = closeImgAlpha;
        var r = closeBtnRect(i);
        p.drawImage(closeImg, r.x, r.y );
        }
    p.globalAlpha = 1;

    var animSpeed = 0.025;
    if( my<50 ){
      triangleAlpha = Math.min(1.0, triangleAlpha+animSpeed);
      if( triangleAlpha<1.0 )
        window.requestAnimFrame(paintEvent);
      } else {
      triangleAlpha = Math.max(0.0, triangleAlpha-animSpeed);
      if( triangleAlpha>0.0 )
        window.requestAnimFrame(paintEvent);
      }

    if( my > drawQBitButtonRect( func.qBitsCount-1 ).y ){
      closeImgAlpha = Math.min(1.0, closeImgAlpha+animSpeed);
      if( closeImgAlpha<1.0 )
        window.requestAnimFrame(paintEvent);
      } else {
      closeImgAlpha = Math.max(0.0, closeImgAlpha-animSpeed);
      if( closeImgAlpha>0.0 )
        window.requestAnimFrame(paintEvent);
      }
    }

  function mouseMove( event ){
    mx = event.pageX - editor.offsetLeft;
    my = event.pageY - editor.offsetTop;

    mOver.x = Math.round( (mx-dx+spaceX/2)/spaceX );
    mOver.y = Math.round( (my-dy+spaceY/2)/spaceY );

    window.requestAnimFrame(paintEvent);
    }

  function mouseUp( event ){
    mx = event.pageX - editor.offsetLeft;
    my = event.pageY - editor.offsetTop;

    var r = drawQBitButtonRect(func.qBitsCount);
    if( r.x < mx && mx < r.x+r.w &&
        r.y < my && my < r.y+r.h ){
      func.setQBitCount(func.qBitsCount+1);
      //gates.modGate( func.name, func.qBitsCount );
      }

    for( var i=0; i<func.qBitsCount; ++i ){
      var r = drawQBitButtonRect(i);
      if( r.x < mx && mx < r.x+r.w &&
          r.y < my && my < r.y+r.h ){
        func.selectedQBit = i;
        editor.argName.value = func.qBitNames[i];
        }
      }

    eachEditCride( function( q, i, r ){
      var prefix = "#";

      if( mOver.x-r <=2 && r-mOver.x<=1 &&
          ( func.editColumn===r || func.editColumn==-1 ) ){
        if( r===mOver.x || r+1===mOver.x )
          p.globalAlpha = 0.5; else
          p.globalAlpha = 0.2;

        var re = editorCrideRect(i,r);
        if( (mx-re.x)*(mx-re.x) + (my-re.y)*(my-re.y)<re.r*re.r &&
            func.gates[r]==="" ){
          q.selected = !q.selected;

          if( q.selected ){
            q.argId = func.eArgsCount;
            } else {
            for( var i=0; i<func.qBitsCount; ++i ){
              if( func.columns[r][i].selected &&
                  func.columns[r][i].argId>q.argId  ){
                func.columns[r][i].argId--;
                func.columns[r][i].name = prefix+(func.columns[r][i].argId+1);
                }
              }
            q.argId = -1;
            }

          if( q.selected )
            func.eArgsCount++; else
            func.eArgsCount--;

          if( q.selected )
            q.name = prefix+(q.argId+1); else
            q.name = "";

          if( func.eArgsCount>0 )
            func.editColumn = r; else
            func.editColumn = -1;

          editor.setArgsCount( func.eArgsCount );
          }
        }
      });

    for( var i=0; i<=func.columns.length; ++i ){
      var re = triangleRect(i);
      if( re.x < mx && mx < re.x+re.w &&
          re.y < my && my < re.y+re.h ){
        if( i<=func.editColumn )
          func.editColumn++;

        func.insertColumn(i);
        }
      }


    for( var i=0; i<func.columns.length; ++i ){
      var r = closeBtnRect(i);
      if( r.x < mx && mx < r.x+r.w &&
          r.y < my && my < r.y+r.w ){
        func.eraseColumn(i);
        if( func.editColumn===i ){
          func.editColumn = -1;
          func.eArgsCount = 0;
          }
        }
      }

    window.requestAnimFrame(paintEvent);
    //testJSON();
    }

  function setupEditingFunction( name, argN ){
    if( functions[name] === undefined ){
      functions[name] = makeFunc(argN);
      functions[name].name = name;
      functionsNames[functionsNames.length] = name;
      }

    func = functions[name];
    window.requestAnimFrame(paintEvent);
    }

  function onFunctionDelete( name ){
    for( var i=0; i<functionsNames.length; ++i ){
      if( functions[ functionsNames[i] ]!==undefined )
        functions[ functionsNames[i] ].onFunctionDelete(name);
      }

    for( var i=0; i<functionsNames.length; ++i )
      if( functionsNames[i]===name ){
        functionsNames.splice(i,1);
        }

    functions[name] = undefined;
    window.requestAnimFrame(paintEvent);
    }

  /*
   vector<Function> proj;

   struct Function{
     vector<Column> circuit;
     vector<string> labels;
     string         name;
     };

   struct Column{
     string __gate;// [0]
     int argPos[];// [1], [2], ...
     };

   example:
     [
      {'circuit': [['H', 0],
                  ['CNOT', 1, 0],
                  ['H', 2],
                  ['example', 0, 1, 3],
                  ['CNOT', 2, 1],
                  ['SWAP', 0, 1]],
       'labels': ['x', 'y', 'z', 't'],
       'name'  : 'main'
       }
     ]
  */
  function setProject( proj ){    
    for( var i=0; i<functionsNames.length; ++i ){
      gates.delGate( functionsNames[i] );
      }

    functions      = [];
    functionsNames = [];

    func           = undefined;

    var proto = JSON.parse( proj );
    for( var i=0; i<proto.length; ++i ){
      var f = proto[i];

      functionsNames[functionsNames.length] = f.name;
      functions[f.name] = makeFuncFromJSON(f);
      }

    func = functions[functionsNames[0]];

    fnList.reset( functionsNames );
    window.requestAnimFrame(paintEvent);
    for( var i=0; i<functionsNames.length; ++i ){
      if( functionsNames[i]!=="main" )
        gates.addGate( functionsNames[i], functions[functionsNames[0]].qBitsCount );
      }

    editor.setArgsCount( 0 );
    }
  editor.setProject = setProject;

  function getProjectAsJson(){
    var ret = new Array(functionsNames.length);

    for( var i=0; i<functionsNames.length; ++i ){
      var fn = functions[ functionsNames[i] ].toJSON();
      ret[i] = fn;
      }

    var s = JSON.stringify(ret);
    return s;
    }
  editor.getProjectAsJson = getProjectAsJson;

  function testJSON(){
    var p = getProjectAsJson();
    alert( p );
    setProject(p);
    }

  editor.onFunctionDelete     = onFunctionDelete;
  editor.setupEditingFunction = setupEditingFunction;

  editor.paintEvent  = paintEvent;
  editor.onmousemove = mouseMove;
  editor.onmouseup   = mouseUp;

  editor.test     = testJSON;

  editor.argName  = document.getElementById("argName");
  editor.argName.value = "Q_n";

  function setQBitName( fn, id, name ){
    for( var i=0; i<functionsNames.length; ++i )
      if( functions[ functionsNames[i] ]!==undefined ){
        functions[ functionsNames[i] ].setQBitName( fn, id, name );
        }
    }

  editor.updateQbitName = function(){
    var s = editor.argName.value;
    for( var i=0; i<s.length; ++i )
      if( !( ('0'<=s[i] && s[i]<='9')||
             ('a'<=s[i] && s[i]<='z')||
             ('A'<=s[i] && s[i]<='Z')||
             ( s[i]==='_') ) )
        return;

    if( s.length<1 )
      return;

    if( func.selectedQBit!==-1 ){
      setQBitName( func, func.selectedQBit, s );
      }

    window.requestAnimFrame(paintEvent);
    }

  editor.argName.oninput = editor.updateQbitName;

  editor.addFunction = function( f ){
    if( func.editColumn===-1 )
      return;

    if( f.argCount!==func.eArgsCount )
      return;

    func.gates[ func.editColumn ] = f.name;
    func.eArgsCount =  0;

    for( var i=0; i<functionsNames.length; ++i ){
      if( functionsNames[i]===f.name ){
        var fn = functions[f.name];
        var c = func.columns[func.editColumn];
        for( var r=0; r<func.qBitsCount; ++r ){
          if( c[r].argId!==-1)
            c[r].name = fn.qBitNames[c[r].argId];
          }
        }
      }

    func.editColumn = -1;
    editor.setArgsCount( 0 );
    window.requestAnimFrame(paintEvent);
    }

  return editor;
  }

var editor = new Editor( document.getElementById("editor") );
editor.paintEvent();
