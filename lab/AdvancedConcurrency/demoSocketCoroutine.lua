
// lua 伪代码

function read(socket)
    local data = {}
    local total = 10 // 从socket读出10个字节
    local readCounter = 0 // 已读出的字节数

    while true do
        local newData = socket.read() // 非阻塞
        data.append(newData)
        readCounter += newData.length
        if readCounter != total // socket的数据还没ready, yield
            coroutine.yield()
        else // 已读出所有数据
            break
    end

    return data
end

function write(socket, data)
    local writeCounter = 0
    local total = data.length
    
    while true do
        writeCounter += socket.write(data, writeCounter) // 非阻塞, 返回本次写入的字节数
        if writeCounter != total // socket 不够 ready 写出所有数据, yield
            coroutine.yield()
        else // 已写入所有数据
            break
    end
end
    

function echo()
    socket = accept()

    data = read(socket)
    write(socket, data)

    close(socket)
end

clients = {}
clientCount = 100
for range(clientCount) // 创建100 个 echo 的 coroutine, 并放进 clients
    client = coroutine.create(echo)
    clients.put(client)
end

while clients notEmpty
    /*
      假设selector是个系统模块, select() 对每个 IO Ready 的 client coroutine 做resume()
      如果全都不IO Ready, 则 该方法会阻塞
    */
    selector.select(clients)
    for client in clients
        if client finished
            clients.remove(client)
    end
end
