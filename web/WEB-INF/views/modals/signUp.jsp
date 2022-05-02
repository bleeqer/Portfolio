
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="signUp-modal" tabindex="-1" class="modal fade" aria-hidden="true" aria-labelledby="modal-title">

    <div class="d-flex flex-column modal-dialog border-grey rounded-6 border-all" style="top: calc(50% - 25px); transform: translateY(-50%); background-color: white !important; min-height: 400px; max-width: 90vw; width: 620px;">
        <div class="d-flex flex-column modal-content" style="height: 100%; border: none; background-color: transparent;">

            <%--Header--%>
            <div class="px-3 pt-3 pb-1">
                <div class="font-bold color-gray-dark" style="font-size: 18px;">
                    Sign Up
                </div>
            </div>

            <%--Form and footer--%>
            <div>

                <%--Form--%>
                <div class="border-bottom-gray-light" style="padding-bottom: 13.5px;">
                    <div>
                        <div class="p-3">

                            <%--Sign up form--%>
                            <form id="signUp-form" action="">

                                <sec:csrfInput/>

                                <%--Name--%>
                                <div class="mb-3">

                                    <%--Label--%>
                                    <div class="mb-1">
                                        <div>
                                            <label class="font-bold color-gray-dark" for="register-name" style="font-size: 13px;">Name</label>
                                        </div>
                                    </div>

                                    <%--Input--%>
                                    <div class="d-flex align-items-center input-container color-gray-dark rounded-1 p-2 border-gray-light hover-border-color-blue" style="min-height: 45px; transition: border 250ms ease-in-out; border: 1px solid transparent;">
                                        <input class="input-focus" type="text" id="register-name" style="width: 100%; outline: none; border: none; font-size: 15px; line-height: 1.4;" placeholder="Your name" autocomplete="off">
                                    </div>

                                </div>

                                <%--Email--%>
                                <div class="mb-3">

                                    <%--Label--%>
                                    <div class="mb-1">
                                        <div>
                                            <label class="font-bold color-gray-dark" for="register-email" style="font-size: 13px;">Email</label>
                                        </div>
                                    </div>

                                    <%--Input--%>
                                    <div class="d-flex align-items-center input-container color-gray-dark rounded-1 p-2 border-gray-light hover-border-color-blue" style="min-height: 45px; transition: border 250ms ease-in-out; border: 1px solid transparent;">
                                        <input class="input-focus" type="text" id="register-email" style="width: 100%; outline: none; border: none; font-size: 15px; line-height: 1.4;" placeholder="Your email" autocomplete="off">
                                    </div>

                                </div>

                                <%--Password--%>
                                <div class="mb-3">

                                    <%--Label--%>
                                    <div class="mb-1">
                                        <div>
                                            <label class="font-bold color-gray-dark" for="register-password" style="font-size: 13px;">Password</label>
                                        </div>
                                    </div>

                                    <%--Input--%>
                                    <div class="d-flex align-items-center color-gray-dark rounded-1 p-2 border-gray-light hover-border-color-blue" style="min-height: 45px; transition: border 250ms ease-in-out; border: 1px solid transparent;">
                                        <input class="input-focus" type="password" id="register-password" style="width: 100%; outline: none; border: none; font-size: 15px; line-height: 1.4;" placeholder="Your password" autocomplete="off">
                                    </div>

                                </div>

                                <%--Occupation--%>
                                <div class="mb-3">

                                    <%--Label--%>
                                    <div class="mb-1">
                                        <div>
                                            <label class="font-bold color-gray-dark" for="register-occupation" style="font-size: 13px;">Occupation</label>
                                        </div>
                                    </div>

                                    <%--Input--%>
                                    <div class="d-flex align-items-center color-gray-dark rounded-1 p-2 border-gray-light hover-border-color-blue" style="min-height: 45px; transition: border 250ms ease-in-out; border: 1px solid transparent;">
                                        <input class="input-focus" type="text" id="register-occupation" style="width: 100%; outline: none; border: none; font-size: 15px; line-height: 1.4;" placeholder="describe your occupation concisely" autocomplete="off">
                                    </div>

                                </div>

                            </form>
                        </div>
                    </div>
                </div>

                <%--Footer--%>
                <div class="px-3 py-2">
                    <div class="d-flex justify-content-end align-items-center">
                        <button id="register-button" class="btn btn-primary rounded-pill" style="height: 38px; font-size: 14px; padding: 0 20px;">
                            <div>Register</div>
                        </button>
                    </div>
                </div>
            </div>



        </div>
        </div>
    </div>
</div>
