<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 17/06/2021
  Time: 6:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="modal fade" id="settingModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
    aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-dialog-zoom" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title"><i class="ti-settings"></i> Settings</h5><button class="close" type="button"
                    data-dismiss="modal" aria-label="Close"><i class="ti-close"></i></button>
            </div>
            <div class="modal-body">
                <ul class="nav nav-tabs" role="tablist">
                    <li class="nav-item"><a class="nav-link active" data-toggle="tab" href="#account" role="tab"
                            aria-controls="account" aria-selected="true">Account</a></li>
                    <li class="nav-item"><a class="nav-link" data-toggle="tab" href="#notification" role="tab"
                            aria-controls="notification" aria-selected="false">Notification</a></li>
                    <li class="nav-item"><a class="nav-link" data-toggle="tab" href="#contact" role="tab"
                            aria-controls="contact" aria-selected="false">Security</a></li>
                </ul>
                <div class="tab-content">
                    <div class="tab-pane show active" id="account" role="tabpanel">
                        <div class="form-item custom-control custom-switch">
                            <input class="custom-control-input" id="customSwitchMode" type="checkbox" />
                            <label class="custom-control-label" for="customSwitchMode">Developer mode options</label>
                        </div>
<%--                        <div class="form-item custom-control custom-switch"><input class="custom-control-input"--%>
<%--                                id="customSwitch1" type="checkbox" checked="" /><label class="custom-control-label"--%>
<%--                                for="customSwitch1">Allow connected contacts</label></div>--%>
<%--                        <div class="form-item custom-control custom-switch"><input class="custom-control-input"--%>
<%--                                id="customSwitch2" type="checkbox" checked="" /><label class="custom-control-label"--%>
<%--                                for="customSwitch2">Confirm message requests</label></div>--%>
<%--                        <div class="form-item custom-control custom-switch"><input class="custom-control-input"--%>
<%--                                id="customSwitch3" type="checkbox" checked="" /><label class="custom-control-label"--%>
<%--                                for="customSwitch3">Profile privacy</label></div>--%>
<%--                        <div class="form-item custom-control custom-switch"><input class="custom-control-input"--%>
<%--                                id="customSwitch5" type="checkbox" checked="" /><label class="custom-control-label"--%>
<%--                                for="customSwitch5">Two-step security--%>
<%--                                verification</label></div>--%>
                    </div>
                    <div class="tab-pane" id="notification" role="tabpanel">
<%--                        <div class="form-item custom-control custom-switch"><input class="custom-control-input"--%>
<%--                                id="customSwitch6" type="checkbox" checked="" /><label class="custom-control-label"--%>
<%--                                for="customSwitch6">Allow mobile--%>
<%--                                notifications</label></div>--%>
<%--                        <div class="form-item custom-control custom-switch"><input class="custom-control-input"--%>
<%--                                id="customSwitch7" type="checkbox" /><label class="custom-control-label"--%>
<%--                                for="customSwitch7">Notifications from your--%>
<%--                                friends</label></div>--%>
<%--                        <div class="form-item custom-control custom-switch"><input class="custom-control-input"--%>
<%--                                id="customSwitch8" type="checkbox" /><label class="custom-control-label"--%>
<%--                                for="customSwitch8">Send notifications by--%>
<%--                                email</label></div>--%>
                    </div>
                    <div class="tab-pane" id="contact" role="tabpanel">
<%--                        <div class="form-item custom-control custom-switch"><input class="custom-control-input"--%>
<%--                                id="customSwitch9" type="checkbox" /><label class="custom-control-label"--%>
<%--                                for="customSwitch9">Suggest changing passwords every--%>
<%--                                month.</label></div>--%>
<%--                        <div class="form-item custom-control custom-switch"><input class="custom-control-input"--%>
<%--                                id="customSwitch10" type="checkbox" checked="" /><label class="custom-control-label"--%>
<%--                                for="customSwitch10">Let me know about suspicious--%>
<%--                                entries to your account</label></div>--%>
<%--                        <div class="form-item">--%>
<%--                            <p><a data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false"--%>
<%--                                    aria-controls="collapseExample"><span class="ti-plus btn-icon"></span> Security--%>
<%--                                    Questions</a></p>--%>
<%--                            <div class="collapse" id="collapseExample">--%>
<%--                                <div class="form-group"><input class="form-control" type="text"--%>
<%--                                        placeholder="Question 1" /></div>--%>
<%--                                <div class="form-group"><input class="form-control" type="text"--%>
<%--                                        placeholder="Question 2" /></div>--%>
<%--                            </div>--%>
<%--                        </div>--%>
                    </div>
                </div>
            </div>
            <div class="modal-footer"><button class="btn btn-primary" type="button">Save</button></div>
        </div>
    </div>
</div>