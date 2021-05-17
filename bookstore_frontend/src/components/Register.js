import React from 'react';

export default class Register extends React.Component {
    render()
    {
        return(
            <div className="center_main_area">
                <form className="box">
                    <div className="field">
                        <label className="label">用户名</label>
                        <div className="control">
                            <input
                                className="input" type="" placeholder="e.g Hello World" />
                        </div>
                    </div>

                    <div className="field">
                        <label className="label">密码</label>
                        <div className="control">
                            <input
                                className="input" type="password" placeholder="包含数字、字母，至少8位" />
                        </div>
                    </div>

                    <div className="field">
                        <label className="label">请确认密码</label>
                        <div className="control">
                            <input
                                className="input" type="password" placeholder="包含数字、字母，至少8位" />
                        </div>
                    </div>


                    <div className="buttons">
                        <button className="button is-link">注册</button>
                    </div>

                </form>
            </div>

        );
    }
}