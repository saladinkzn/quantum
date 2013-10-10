function FnList( fnList ){
  var p = painter( fnList );
  var mx = 0, my = 0;

  var gts = [
        {name:"main"   , sel:0 }
      ];

  function btnRect( id ){
    var hsz = 25;
    var rect = { x:5, y:id*hsz+1, w:fnList.width-10, h:hsz-1 };
    return rect;
    }

  function delBtnRect( i ){
    var rect = btnRect(i);
    var bsz = rect.h-4;

    var r = {x:rect.x+rect.w-bsz, y:rect.y+2, w:bsz, h:bsz};
    return r;
    }

  function paintEvent(){
    fnList.width  = fnList.clientWidth;
    fnList.height = fnList.clientHeight;

    p.font="16px Arial";
    p.strokeStyle = '#0101010F';

    for( var i=0; i<gts.length; ++i ){
      var rect = btnRect(i);
      p.fillStyle   = '#F0F0F0';
      p.globalAlpha = gts[i].sel*0.8+0.2;
      p.roundedRect( rect.x, rect.y, rect.w, rect.h );
      p.globalAlpha = 1;

      var str = gts[i].name;
      var sz  = p.measureText(str);
      p.fillStyle = '#000000';
      var bsz = rect.h-4;
      p.fillText(str, rect.x+(rect.w-bsz)/2-sz.width/2, rect.y+rect.h-5 );

      if( rect.y < my && my <rect.y+rect.h ){
        p.fillStyle   = '#F0F0F0';
        p.roundedRect(rect.x+rect.w-bsz, rect.y+2, bsz, bsz);

        p.fillStyle = '#000000';
        p.fillText("-", rect.x+rect.w-bsz+7, rect.y+2+13);
        }
      }

    var aspeed = 0.02;
    for( var i=0; i<gts.length; ++i ){
      var rect = btnRect(i);
      var old = gts[i].sel;
      if( rect.y <my && my<rect.y+rect.h )
        gts[i].sel = Math.min(1, gts[i].sel+aspeed); else
        gts[i].sel = Math.max(0, gts[i].sel-aspeed);
      if( old!==gts[i].sel )
        window.requestAnimFrame(paintEvent);
      }
    }

  function mouseMove( event ){
    mx = event.pageX - fnList.offsetLeft;
    my = event.pageY - fnList.offsetTop;

    window.requestAnimFrame(paintEvent);
    }

  function mouseUp( event ){
    mx = event.pageX - fnList.offsetLeft;
    my = event.pageY - fnList.offsetTop;

    for( var i=0; i<gts.length; ++i ){
      var r = btnRect(i);
      var d = delBtnRect(i);

      if( d.x < mx && mx < d.x+d.w &&
          d.y < my && my < d.y+d.h ){
        editor.onFunctionDelete( gts[i].name );
        gates.delGate( gts[i].name );
        gts.splice(i, 1);
        return;
        }

      if( r.x < mx && mx < r.x+r.w &&
          r.y < my && my < r.y+r.h ){
        editor.setupEditingFunction( gts[i].name );
        return;
        }
      }
    }

  function mouseLeave( event ){
    mx = -1;
    my = -1;
    window.requestAnimFrame(paintEvent);
    }

  function validateInput(){
    var str = input.value;
    var btn = document.getElementById("addFnButton");
    btn.disabled = (str.length===0);

    for( var i=0; i<gts.length; ++i)
      if( gts[i].name==str )
        btn.disabled = true;

    for( var i=0; i<str.length; ++i )
      if( !(('a'<=str[i] && str[i]<='z') || ('A'<=str[i] && str[i]<='Z')||('0'<=str[i] && str[i]<='9')||str[i]=='_') )
        btn.disabled = true;

    var argN = document.getElementById("argC").value;

    if( argN<=0 || argN>64 )
      btn.disabled = true;
    }

  function add(){
    var str  = document.getElementById("fnname").value;
    var argN = document.getElementById("argC").value - 0;

    if( argN<=0 || argN>64 )
      return;

    for( var i=0; i<gts.length; ++i)
      if( gts[i].name==str )
        return;

    gts.splice(0,0, {name:str, sel:0 });
    window.requestAnimFrame(paintEvent);
    validateInput();

    gates.addGate(str, argN);
    editor.setupEditingFunction( str, argN );
    }

  function reset( gt ){
    gts = [];
    for( var i=0; i<gt.length; ++i ){
      gts[ gts.length ] = {name:gt[i], sel:0};
      }
    window.requestAnimFrame(paintEvent);
    }

  var input = document.getElementById("fnname");
  var num   = document.getElementById("argC");
  num.value = "4";

  input.oninput       = validateInput;
  num.oninput         = validateInput;

  fnList.reset        = reset;
  fnList.add          = add;
  fnList.paintEvent   = paintEvent;
  fnList.onclick      = paintEvent;
  fnList.onmousemove  = mouseMove;
  fnList.onmouseup    = mouseUp;
  fnList.onmouseout   = mouseLeave;

  return fnList;
  }

var fnList = new FnList( document.getElementById("fnList") );
fnList.paintEvent();
