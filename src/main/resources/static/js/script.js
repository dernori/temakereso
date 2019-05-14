var apiHeader = {
  headers: {
    'Content-type': 'application/json',
  }
}

/*----------- HELPER FUNCTIONS ----------- */

Object.byString = function(o, s) {
  s = s.replace(/\[(\w+)\]/g, '.$1'); // convert indexes to properties
  s = s.replace(/^\./, '');           // strip a leading dot
  var a = s.split('.');
  for (var i = 0, n = a.length; i < n; ++i) {
    var k = a[i];
    if (k in o) {
      o = o[k];
    } else {
      return;
    }
  }
  return o;
}

/**
 * Sets the display attribute of the selected object
 * @param selector
 * @param display - change the value to
 */
function setDisplay(selector, display) {
  $(selector)[0].style.display = display;
}

/**
 * Redirects
 * @param path - to redirect to
 */
function redirect(path) {
  window.location.href = path;
}

/**
 * Formats date to the given format
 * @param longTime
 * @returns
 */
function formatDate(longTime, formatText) {
  return moment(longTime).format(formatText);
}

function overlayOn() {
  document.getElementById('overlay').style.display = 'flex'
}

function overlayOff() {
  document.getElementById('overlay').style.display = 'none'
}

function hideMessage(id) {
  window.setTimeout(function() {
    $('#'+id).fadeTo(500, 0).slideUp(500, function(){
      $(this).remove();
    })
  }, 2000)
}