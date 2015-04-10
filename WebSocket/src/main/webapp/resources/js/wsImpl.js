var ws =  ws || {};

if (!ws.impl) {
    
	ws.impl = function () {
        var user;
        var ws;
        var constants = {CONNECTING: 0, OPEN: 1, CLOSING: 2, CLOSED: 3};
        
        var init = function (url, protocols){
            if(!ws){
                ws = new WebSocket(url);
                ws.onopen = onOpen;
                ws.onmessage = onMessage;
                ws.onerror = onError;
                ws.onclose = onClose;
            }
        };
        
        var send = function(message){
            ws.send(message);
        };
        
        var onMessage = function onMessage(event){
            /*  TODO aquí viene la lógica que gestiona los mensajes 
                enviados desde el servidor al cliente
            */
            
            var data= JSON.parse(event.data);
            var messageLine =   '<tr>'+
                                    '<td class="received">'+data.received + '</td>' +
                                    '<td class="user label label-info">' + data.sender + '</td>'+
                                    '<td class="message badge">'+data.message+' </td>'+
                                '</tr>';
            document.getElementById('response').innerHTML+=messageLine;
            notifyMe(data.message);
        };
        
        var close = function(){
            ws.close(); 
        };
        
        var onOpen = function(event){
            alert("Connection is opened");
        };
        
        var onError = function(event){
            alert("Connection has an error");
        };
        
        var onClose = function(event){
            alert("Connection is closed");
        };
                
        return {
            connect : function (url, protocols){
                init(url, protocols);
            },
            
            connectRoom : function (url,room, nick){
                if(!user){
                    user = {};
                    user.nik = nick;
                }
                init(url+"/"+room);
            },
            
            
            sendMessage: function (idInput){
                var inputText = document.getElementById(idInput);
                var msg = '{"message":"' + inputText.value + '", "sender":"'+ user.nik + '", "received":""}';
                send(msg);
            },
            
            send : function (id){
                
                
                var buffer = new ArrayBuffer(8);
                var bytes = new Uint8Array(buffer);
                                
                for (var i=0; i<bytes.length; i++) {
                    bytes[i] = i;
                }
                
                send(JSON.stringify({name:'Charles', age:32, address:'Calle de Costa Rica, 38'})); // JSON
                
                send(document.getElementById(id).value); // dato plano
                send(buffer);  // dato binario
            },
            
            close : function (){
                close();
            }
        };
	}();
}

function notifyMe(message) {
	  // Let's check if the browser supports notifications
	  if (!("Notification" in window)) {
	    alert("This browser does not support desktop notification");
	  }

	  // Let's check if the user is okay to get some notification
	  else if (Notification.permission === "granted") {
	    // If it's okay let's create a notification
	    var notification = new Notification(message);
	  }

	  // Otherwise, we need to ask the user for permission
	  // Note, Chrome does not implement the permission static property
	  // So we have to check for NOT 'denied' instead of 'default'
	  else if (Notification.permission !== 'denied') {
	    Notification.requestPermission(function (permission) {

	      // Whatever the user answers, we make sure we store the information
	      if(!('permission' in Notification)) {
	        Notification.permission = permission;
	      }

	      // If the user is okay, let's create a notification
	      if (permission === "granted") {
	        var notification = new Notification(message);
	      }
	    });
	  }

	  // At last, if the user already denied any notification, and you 
	  // want to be respectful there is no need to bother him any more.
	}