function painter( editor ){
  var p = editor.getContext('2d');
  p.line = function( x1, y1, x2, y2 ){
    p.beginPath()
    p.moveTo(x1,y1);
    p.lineTo(x2,y2);
    p.stroke();
    }

  p.cride = function( x1, y1, R ){
    p.beginPath()
    p.arc( x1, y1, R,0,2*Math.PI);
    p.stroke();
    }

  p.cridef = function( x1, y1, R ){
    p.beginPath()
    p.arc( x1, y1, R,0,2*Math.PI);
    p.fill();
    }

  p.rectangle = function( x1, y1, w, h ){
    p.fillRect  ( x1, y1, w, h );
    p.strokeRect( x1, y1, w, h );
    }

  p.roundedRect =  function (xx,yy, ww,hh, rad) {
    if (typeof(rad) == "undefined")
      rad = 5;

    rad = Math.min( rad, ww/2, hh/2 );

    p.beginPath();
    p.moveTo(xx + rad, yy);
    p.lineTo(xx + ww - rad, yy);
    p.quadraticCurveTo(xx + ww, yy, xx + ww, yy + rad);
    p.lineTo(xx + ww, yy + hh - rad);
    p.quadraticCurveTo(xx + ww, yy + hh, xx + ww - rad, yy + hh);
    p.lineTo(xx + rad, yy + hh);
    p.quadraticCurveTo(xx, yy + hh, xx, yy + hh - rad);
    p.lineTo(xx, yy + rad);
    p.quadraticCurveTo(xx, yy, xx + rad, yy);
    p.closePath();

    p.stroke();
    p.fill();
    };

  return p;
  }

window.requestAnimFrame = (function(){
  return  window.requestAnimationFrame       ||
          window.webkitRequestAnimationFrame ||
          window.mozRequestAnimationFrame    ||
          function( callback ){
            window.setTimeout(callback, 1000 / 60);
          };
})();
