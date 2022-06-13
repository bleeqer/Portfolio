

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
                            <form id="signUp-form" action="/user/create" method="POST">

                                <sec:csrfInput/>

                                <%--Image and Name--%>
                                <div class="d-flex align-items-center pb-2" style="height: 74.5px;">
                                    <%--Image--%>
                                    <div href="" class="me-2">
                                        <div class="rounded-circle position-relative" style="overflow: hidden; height: 100%;">
                                            <img id="profile-upload-image" style="height: inherit;"
                                                 src="/static/img/user.png" alt="">
                                            <div id="profile-image-upload-button" class="d-flex justify-content-center align-items-center rounded-circle position-absolute hover-show" style="cursor: pointer; z-index: 999; top: 50%; left: 50%; transform: translate(-50%, -50%); opacity: 0; transition: opacity 100ms ease-in-out; height: 100%; width: 100%;">
                                                <span class="d-flex justify-content-center align-items-center rounded-circle" style="background-color: rgb(20, 113, 255); width: 30px; height: 30px;">
                                                    <svg width="24" height="24" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                                                        <g fill="none" fill-rule="evenodd">
                                                            <path d="M18.571 5.429h0a2 2 0 0 1 0 2.828l-9.9 9.9-4.24 1.416 1.412-4.245 9.9-9.9a2 2 0 0 1 2.828 0Z" stroke="rgb(255, 255, 255)" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round"></path>
                                                        <path class="" fill="rgb(255, 255, 255)" d="m4.429 19.571 2.652-.884-1.768-1.768z"></path>
                                                        </g>
                                                    </svg>
                                                </span>
                                            </div>
                                        </div>
                                    </div>
                                    <%--Name--%>
                                    <div class="">

                                        <%--Label--%>
                                        <div class="mb-1">
                                            <div>
                                                <label class="font-bold color-gray-dark" for="register-name" style="font-size: 13px;">Name</label>
                                            </div>
                                        </div>

                                        <%--Input--%>
                                        <div class="d-flex align-items-center input-container color-gray-dark rounded-1 p-1 border-gray-light hover-border-color-blue" style="min-height: 35px; transition: border 250ms ease-in-out; border: 1px solid transparent;">
                                            <input class="input-focus" type="text" id="register-name" name="name" style="min-width: 175px; outline: none; border: none; font-size: 15px; line-height: 1.4;" placeholder="Your name" autocomplete="off">
                                        </div>

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
                                    <div class="d-inline-flex align-items-center input-container color-gray-dark rounded-1 p-1 border-gray-light hover-border-color-blue" style="min-height: 35px; transition: border 250ms ease-in-out; border: 1px solid transparent;">
                                        <input class="input-focus" type="text" id="register-email" name="email" style=" width: auto; min-width: 250px; outline: none; border: none; font-size: 15px; line-height: 1.4;" placeholder="Your email" autocomplete="off">
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
                                    <div class="d-inline-flex align-items-center color-gray-dark rounded-1 p-1 border-gray-light hover-border-color-blue" style="min-height: 35px; transition: border 250ms ease-in-out; border: 1px solid transparent;">
                                        <input class="input-focus" type="password" id="register-password" name="password" style="min-width: 160px; outline: none; border: none; font-size: 15px; line-height: 1.4;" placeholder="Your password" autocomplete="off">
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
                                    <div class="d-inline-flex align-items-center color-gray-dark rounded-1 p-2 border-gray-light hover-border-color-blue" style="min-height: 35px; transition: border 250ms ease-in-out; border: 1px solid transparent;">
                                        <input class="input-focus" type="text" id="register-occupation" name="occupation" style="min-width: 250px; outline: none; border: none; font-size: 15px; line-height: 1.4;" placeholder="describe your occupation concisely" autocomplete="off">
                                    </div>

                                </div>
                                <input id="image" type="file" style="display: none;">
                                <input id="photoPath" type="text" name="photo" style="display: none;">
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
