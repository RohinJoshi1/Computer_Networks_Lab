import net from 'net';
const server = net.createServer( socket =>{
    console.log("TCP handshake has been sucessful with " + socket.remoteAddress + ":" + socket.remotePort);
    socket.write("Hello client");
    socket.on("data",data=>{
        console.log("Received Data: " + data.toString());
       })

})
server.listen(8080,"127.0.0.1");
