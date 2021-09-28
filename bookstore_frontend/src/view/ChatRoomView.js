import React, {useEffect, useLayoutEffect,useRef, useState} from "react";


const ChatRoomView=()=>{
    const [type,setType]=useState(0);
    const [msg,setMsg]=useState("");
    const [toUserId,setToUserId]=useState(-1);
    const [isOpen,setIsOpen]=useState(false);
    const websocket = useRef(null);

    useEffect(()=>{

        websocket.current = new WebSocket("ws://localhost:8080/chat");
        // websocket.current.onmessage = onMessage;
        websocket.current.onclose=()=>{setIsOpen(false);alert("websocket close!");}
        websocket.current.onopen=()=>{console.log("open socket");setIsOpen(true);}
        websocket.current.onerror=()=>{alert("websocket error")}

        return () => {
            websocket.current.close();
        };
    },[]);

    useEffect(() => {
        if (!websocket.current) return;

        websocket.current.onmessage = onMessage;
    }, []);


    const onMessage=(evt)=>{
        console.log(evt);

        const data=JSON.parse(evt.data);
        const status=data.status;
        if(status<0)
        {
            alert("error")
            return
        }
        const msg=data.msg;
        const type=data.type;
        const from=data.from;
        const time=data.time;
        let backMsg="("+time+") ["+from+"] "+msg;
        if(Number(type)===0)
        {
            backMsg="私聊"+backMsg+"\n";
        }
        else if(Number(type)===1)
        {
            backMsg="公开"+backMsg+"\n";
        }
        document.getElementById("msg_box").value += backMsg;
    }



    const sendMsg=()=>{
        console.log("type:"+type);
        console.log(msg);
        console.log(toUserId);

        if(!websocket.current.OPEN)
        {
            alert("websocket close");
        }

        if(type===0)
        {
            console.log("send");
            const data={"type":0,"to":toUserId,"msg":msg}
            websocket.current.send(JSON.stringify(data));
        }
        else if(type===1)
        {
            console.log("send");
            const data={"type":1,"msg":msg}
            websocket.current.send(JSON.stringify(data));
        }

    }

    return(
        <div>
            <section className="hero is-info">
                <div className="hero-body">
                    <p className="title">
                        西西弗聊天室
                    </p>
                    <p className="subtitle">
                        分享你所热爱的书籍
                    </p>
                </div>
            </section>
            <textarea id={"msg_box"} className="textarea is-primary" readOnly={true} cols="70" rows="15" o></textarea>
            <h1 className="title">发送消息</h1>
            <textarea className="textarea is-info" cols="70" rows="2" onChange={(e)=>{setMsg(e.target.value)}}></textarea>

            <div>


            </div>
            <div className="columns">
                <div className="column is-4">
                    <div className="select">
                        <select onChange={event => {setType(parseInt(event.target.value))}}>
                            <option value={0}>私密消息</option>
                            <option value={1}>发送给全体成员</option>
                        </select>
                    </div>
                </div>
                <div className="column is-4">
                    <input className="input is-primary" type="text" placeholder="请输入目标用户的ID" onChange={event => {setToUserId(event.target.value)}}/>
                </div>
                <div className="column is-2">
                    <button className="button is-primary" onClick={sendMsg}>发送</button>
                </div>
            </div>

        </div>
    )
}

export default ChatRoomView