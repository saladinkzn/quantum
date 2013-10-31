function Gates( gates ){
  var p = painter( gates );
  var mx = 0, my = 0;
  var argCount = 0;

  var gts = [
        {name:"H"      , argCount:1, sel:0 },
        {name:"not"    , argCount:1, sel:0 },
        {name:"cnot"   , argCount:2, sel:0 }
      ];

  function btnRect( id ){
    var hsz = 25;
    var rect = { x:5, y:id*hsz+1, w:gates.width-10, h:hsz-1 };
    return rect;
    }

  function paintEvent(){
    gates.width  = gates.clientWidth;
    gates.height = gates.clientHeight;

    p.font="16px Arial";
    p.strokeStyle = '#0101010F';

    for( var i=0; i<gts.length; ++i ){
      var rect = btnRect(i);

      if( gts[i].argCount===argCount )
        p.fillStyle   = '#186fff'; else
        p.fillStyle   = '#F0F0F0';

      p.globalAlpha = gts[i].sel*0.8+0.2;
      p.roundedRect( rect.x, rect.y, rect.w, rect.h );
      p.globalAlpha = 1;

      var str = gts[i].name;
      var sz  = p.measureText(str);
      p.fillStyle = '#000000';
      p.fillText(str, rect.x+rect.w/2-sz.width/2, rect.y+rect.h-5 );
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
    mx = event.pageX - gates.offsetLeft;
    my = event.pageY - gates.offsetTop;
 
    window.requestAnimFrame(paintEvent);
    }

  function mouseUp( event ){
    mx = event.pageX - gates.offsetLeft;
    my = event.pageY - gates.offsetTop;
    var b=true;
    var i=0; 
    while( i<gts.length && b==true){
        var rect = btnRect(i);
        if( rect.y <my && my<rect.y+rect.h && rect.x <mx && mx<rect.x+rect.w){
            b=false; i--;
         }
         i++;
     }
    if (b==false){
        editor.addFunction( gts[i] );
     }
   }

  function mouseLeave( event ){
    mx = -1;
    my = -1;
    window.requestAnimFrame(paintEvent);
    }

  function setArgsCount( c ){
    argCount = c;
    window.requestAnimFrame(paintEvent);
    }
  
  function delGate(g){
    var i=0; var b=true;
    while( i<gts.length && b==true){
        if( gts[i].name==g){
            b=false; i--;
         }
         i++;
   }

   if (i<gts.length) {
       gts.splice(i,1);
   }

   window.requestAnimFrame(paintEvent);
  }

function modGate(g, argCount){
    var i=0; var b=true;
    while( i<gts.length && b==true){
        if( gts[i].name==g){
            b=false; i--;
         }
         i++;
   }
   if (i<gts.length) {
       gts[i].argCount=argCount;
   }

   window.requestAnimFrame(paintEvent);

}

function addGate(g,argCount1){
   var i=0; var b=true;
    while( i<gts.length && b==true){
        if( gts[i].name==g){
            b=false; i--;
         }
         i++;
   }
    if (b==true){
        gts.push({name:g      , argCount:argCount1, sel:0 });
     }
     else alert("function name was use");
    window.requestAnimFrame(paintEvent);
  }

  gates.paintEvent   = paintEvent;
  gates.onclick      = paintEvent;
  gates.onmousemove  = mouseMove;
  gates.onmouseup    = mouseUp;
  gates.onmouseout   = mouseLeave;
  gates.setArgsCount = setArgsCount;

  gates.addGate = addGate;
  gates.modGate = modGate;
  gates.delGate = delGate;

  return gates;
  }

var gates = new Gates( document.getElementById("gates") );
gates.paintEvent();
editor.setArgsCount = gates.setArgsCount;
