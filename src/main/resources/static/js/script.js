var apiHeader = { 
	headers: {
		'Content-type': 'application/json',
	}
}

/*----------- HELPER FUNCTIONS ----------- */

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