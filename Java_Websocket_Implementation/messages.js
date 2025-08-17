let socket;

function joinRoom() {
  const user = document.getElementById("username").value
  socket = new WebSocket("ws://localhost:8887");

  socket.onopen = () => {
    socket.send(user + " Joined");
    log("Connected and joined ");
    const title = document.getElementById("t1")
    title.classList.add("connected")
  };

  socket.onmessage = (event) => {
    log(event.data);
  };

  socket.onerror = (error) => {
    log("Error: " + error);
  };
}

function sendMessage() {
  const msg = document.getElementById("msg").value;
  const user = document.getElementById("username").value;
  socket.send(user + ": " +  msg);
  log(user + ": " + msg)
  document.getElementById("msg").value = "";
}

function log(message) {
  const chat = document.getElementById("chat");
  chat.value += message + "\n";
}