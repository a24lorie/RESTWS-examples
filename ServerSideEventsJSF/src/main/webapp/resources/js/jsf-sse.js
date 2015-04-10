if (!JSF) {

	/**
	 * The top level global namespace for JavaServer Faces Server Sent Event
	 * functionality. 
	 * @name JSF
	 * @namespace
	 */
	var JSF = {};

	/**
	 * The namespace for Server Sent Event functionality. 
	 * @name JSF.sse
	 * @namespace
	 * @exec
	 */
	JSF.sse = function() {

		var eventSource = null;

		var getEventSource = function getEventSource(url) {
			//SSE si compatible
			if(typeof(EventSource)!=="undefined")
			{
				url = 'http://' + document.location.host + url;
				eventSource = new EventSource(url);
			}
			else
			{
			    alert("Sorry, your browser does not support server-sent events...");
			}  
		};

		return {

			/**
			 * Connect to a server end point. 
			 * <p><b>Usage:</b></p>
			 * <pre><code>
			 * JSF.sse.connect(url, {events: 'stock:stockHandler time:clockHandler'});
			 * ... 
			 * function stockHandler(event) {
			 * ... 
			 * }
			 * </pre></code>
			 *
			 * @member JSF.sse
			 * @param url The URL of a valid server end point that will deliver messages. 
			 * @param eventOptions The set of events consisting of event name:handler name pairs
			 *   associating an event name from the server, and the name of the handler that
			 *   will process the event.. 
			 */
			connect: function connect(url, eventOptions) {
				if (url === null) {
					throw new Error("Must specify URL");
				}
				getEventSource(url);
				if (eventSource !== null) {
					if (eventOptions !== null) {
						for (var i in eventOptions) {
							JSF.sse.addOnEvent(i, eventOptions[i]);
						}
					}
				}

			},

			/**
			 * Register a callback for error handling. 
			 * <p><b>Usage:</b></p>
			 * <pre><code>
			 * JSF.sse.addOnError(handleError); 
			 * ... 
			 * var handleError = function handleError(data) {
			 * ... 
			 * }
			 * </pre></code>
			 *
			 * @member JSF.sse
			 * @param callback a reference to a function to call on an error
			 */
			addOnError: function addOnError(callback) {
				if (eventSource !== null) {
					if (typeof callback === 'function') {
						eventSource.addEventListener('error', callback, false);
					}
				}

			},

			/**
			 * Register a callback for event handling. 
			 * <p><b>Usage:</b></p>
			 * <pre><code>
			 * JSF.sse.addOnEvent('timeEvent', handleEvent); 
			 * ... 
			 * var handleEvent = function handleEvent(data) {
			 * ... 
			 * }
			 * </pre></code>
			 *
			 * @member JSF.sse
			 * @param eventName the event name associated with the message. 
			 * @param callback a reference to a function to call for processing messages with a specific event
			 * name. 
			 */
			addOnEvent: function addOnEvent(eventName, callback) {
				if (eventSource !== null) {
					if (typeof callback === 'function' && eventName !== null
							&& typeof eventName !== 'undefined') {
						eventSource.addEventListener(eventName, callback, false);
					}
				}

			}
		};

	}();
} 