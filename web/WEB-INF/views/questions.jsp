<%--
  Created by IntelliJ IDEA.
  User: linux-king
  Date: 2/13/22
  Time: 8:16 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE HTML>
<html>
<head>
  <title>Title</title>
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <script src="/js/tinymce/tinymce.min.js"></script>
  <script src="/js/tinymce/jquery.tinymce.min.js"></script>

  <sec:csrfMetaTags/>

  <%--  Bootstrap  --%>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>

  <%-- Bootstrap Icon --%>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">

  <%--CSS--%>
  <link rel="stylesheet" type="text/css" href="/static/css/utils.css">
  <link rel="stylesheet" type="text/css" href="/static/css/main.css">

  <style>
    input, textarea, button, a {
      color: rgb(213, 214, 214);
      background-color: transparent;
    }

    html, body {
      box-sizing: border-box;
      padding: 0;
      margin: 0;
      font-size: 16px;
      border: none;
      background-color: #181818;
      color: rgb(213, 214, 214);
    }

    .icon_svg-stroke {
      stroke: rgb(177, 179, 182);
    }

    a {
      text-decoration: none;
      color: inherit;
    }

    a:hover {
      color: inherit;
    }
  </style>

</head>
<body>

  <%--Navbar--%>
  <%@ include file="/WEB-INF/views/common/navbar.jsp" %>
  <%--Search focus overlay--%>
  <div class="position-fixed bg-transparent-dark search-overlay"></div>

  <div class="container-fluid m-0" style="padding-top: 50px;">
    <div class="d-flex mx-auto pt-3 px-2" style="max-width: 1002px;">

      <%--카테고리 리스트--%>
        <div class="pb-5 position-fixed" style="width: 166px;">
          <div>

            <%--개별 카테고리 컨테이너--%>
            <div class="mb-1 rounded-1 bg-hover-whiten-light">
              <a class="text-decoration-none" href="">
                <div class="p-2 d-flex" style="height: auto;">
                  <%--카테고리 아이콘--%>
                  <div class="me-2">
                    <div class="d-flex rounded-1 overflow-hidden">
                      <img width="18" height="18" src="/static/img/python.jpeg" alt="IMG">
                    </div>
                  </div>

                  <%--카테고리 이름--%>
                  <div class="d-flex">
                    <div class="d-flex font-size-1">
                      <div class="text-center d-flex align-items-center">Python</div>
                    </div>
                  </div>
                </div>
              </a>
            </div>

          </div>
        </div>

        <%--질문글 리스트--%>
        <div style="width: 572px; margin-left:172px;">
          <div>
            <div>

              <%--질문글--%>
              <div class="mb-3 mx-1" style="">

                <%--배경색--%>
                <div class="rounded-3 shadow-sm border border-color-dark bg-dark position-relative bg-hover-whiten">

                  <%--Questions for you--%>
                  <div class="px-3 py-2 border-bottom-grey">
                    <div class="d-flex justify-content-between">
                      <div class="d-flex align-items-center">
                        <div class="d-inline-flex rounded-2 overflow-hidden">
                          <div class="d-flex justify-content-center align-items-center bg-red" style="width: 24px; height: 24px;">
                            <span class="d-inline-flex justify-content-center align-items-center" style="width: 16px; height: 16px; line-height: 16px;">
                              <svg width="16" height="16" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                                <path d="m12 16.618-4.944 2.599L8 13.71 4 9.812l5.528-.803L12 4l2.472 5.01L20 9.811l-4 3.9.944 5.505z" stroke="rgb(255, 255, 255)" stroke-width="2.1" fill="rgb(255, 255, 255)" stroke-linejoin="round"></path>
                              </svg>
                            </span>
                          </div>
                        </div>
                        <div class="d-inline-flex ms-2">
                          <div class="color-grey" style="font-size: 13px;">
                            Questions for you
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>

                  <div class="" style="height: 100%;">


                    <div>

                      <%--질문글--%>
                      <div>
                        <div class="pt-4 px-4">

                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>

        <%--주간 조회, 좋아요 베스트--%>
        <div style="width: 238px; margin-left: 20px;">

          <%--주간 조회수 베스트--%>
          <div class="bg-dark rounded-3 mb-2">
            <div class="px-4 py-2 border-bottom-grey">
              <div class="text-center">
                <b>Most viewed</b>
              </div>
            </div>

            <div class="best-title px-3 bg-hover-whiten-light py-1 dark border-bottom-grey">
              <div>
                <div>
                  <div style="text-overflow: ellipsis; white-space: nowrap; overflow: hidden; width: 206px;">hover 시 끝까지 다 보여주기 hover 시 끝까지 다 보여주기 hover 시 끝까지 다 보여주기</div>
                </div>
              </div>
            </div>

          </div>

          <%--주간 좋아요 베스트--%>
          <div class="bg-dark rounded-3">
            <div class="px-4 py-2 border-bottom-grey">
              <div class="text-center">
                <b>Most liked</b>
              </div>
            </div>

            <div class="best-title px-3 bg-hover-whiten-light py-1 dark border-bottom-grey">
              <div>
                <div>
                  <div style="text-overflow: ellipsis; white-space: nowrap; overflow: hidden; width: 206px;">hover 시 끝까지 다 보여주기 hover 시 끝까지 다 보여주기 hover 시 끝까지 다 보여주기</div>
                </div>
              </div>
            </div>
          </div>
        </div>
        </div>
      </div>
    </div>
  </div>
  <%@ include file="/WEB-INF/views/modals/question-modal.jsp" %>
<%--  <div id="ex" class="modal">gg--%>
<%--    <a href="#" rel="modal:close">Close</a>--%>
<%--  </div>--%>
<%--  <a href="#ex" rel="modal:open">dd</a>--%>
<%--  <button type="button" class="btn btn-lg btn-danger" data-toggle="popover" title="Popover title" data-content="And here's some amazing content. It's very engaging. Right?">Click to toggle popover</button>--%>
<%--  <h2>게시판</h2>--%>
<%--&lt;%&ndash;  <sec:authorize access="!isAuthenticated()"><span id="login-button">Login</span></sec:authorize>&ndash;%&gt;--%>
<%--&lt;%&ndash;  <span id="login-button">Login</span>&ndash;%&gt;--%>
<%--&lt;%&ndash;  <span id="register-button">Register</span>&ndash;%&gt;--%>
<%--&lt;%&ndash;  <sec:authorize access="isAuthenticated()">Logout</sec:authorize>&ndash;%&gt;--%>
<%--&lt;%&ndash;  <sec:authorize access="hasRole('ADMIN')"><a href="#">Manage Users</a></sec:authorize>&ndash;%&gt;--%>
<%--  <table id="question-table">--%>
<%--    <c:forEach var="category" items="${categories}">--%>
<%--      <a href="/topic/${category.categoryName}">${category.categoryName}</a>--%>
<%--    </c:forEach>--%>
<%--    <span id="ask-button">ASK QUESTION</span>--%>
<%--    <div id="question-list">--%>
<%--      <c:forEach var="question" items="${questions}">--%>
<%--        <tr class="question-row" data-ques_no="${question.quesNo}">--%>
<%--          <td>${question.quesNo}</td>--%>
<%--          <td><a href="/question/${question.quesNo}">${question.title}</a></td>--%>
<%--          <td>${question.writer}</td>--%>
<%--          <td>${question.regDate}</td>--%>
<%--          <td>${question.viewCnt}</td>--%>
<%--          <td><span id="answer-button">ANSWER</span></td>--%>
<%--        </tr>--%>
<%--      </c:forEach>--%>
<%--    </div><br/>--%>
<%--    <span id="more-button">More</span>--%>

<%--  </table>--%>


<%--  <div id="topic" data-selected-topic="${selectedTopic}" style="display: none;"></div>--%>
<%--&lt;%&ndash;  <%@ include file="/WEB-INF/views/modals/userLoginForm.jsp" %>&ndash;%&gt;--%>

<%--  <form id="logout-form" action="/user/logout" method="POST" style="display: none;">--%>
<%--    <input id="input-csrf" type="text" name="_csrf">--%>
<%--  </form>--%>
<%--  <span id="logout-button"><a href="/user/logout">LOG OUT</a></span>--%>
<%--  <script>--%>
<%--    async function initEditor () {--%>

<%--      await tinymce.init({--%>
<%--        selector: "#content",--%>
<%--        menubar: false,--%>
<%--        statusbar: false,--%>
<%--        toolbar: false,--%>
<%--        height: $("#modal-question").height() - $("title").height(),--%>
<%--        // setup: function (editor) {--%>
<%--        //   editor.on('change', function () {--%>
<%--        //     editor.save()--%>
<%--        //   })--%>
<%--        // },--%>
<%--        relative_urls: false,--%>
<%--        // remove_script_host: false--%>

<%--        // setup: function(editor) {--%>
<%--        //--%>
<%--        //   editor.on('init', function(e) {--%>
<%--        //     console.log("editor initialized")--%>
<%--        //   })--%>
<%--        // }--%>
<%--      })--%>
<%--    }--%>

<%--    const observer = new MutationObserver(function() {--%>

<%--      if ($(".modal").css("display") === "none") {--%>

<%--        // 텍스트 에디터에 저장된 값 지우기--%>
<%--        tinymce.activeEditor.setContent("")--%>

<%--        // 기존 텍스트 에디터 객체 지우기--%>
<%--        tinymce.remove()--%>

<%--        // 인풋 태그에 저장된 값 지우기--%>
<%--        $("input").val("")--%>


<%--      }--%>
<%--    })--%>

<%--    // 변경 감시 대상--%>
<%--    const target = document.getElementById('modal-question');--%>

<%--    // style 속성 감시--%>
<%--    observer.observe(target, { attributes : true, attributeFilter : ['style'] });--%>

<%--  </script>--%>

<%--  <script>--%>

<%--    const name = $("meta[name='_csrf_parameter']").attr("content")--%>
<%--    const header = $("meta[name='_csrf_header']").attr("content")--%>
<%--    const token = $("meta[name='_csrf']").attr("content")--%>

<%--    $(".edit-button").click(async function (e) {--%>

<%--      e.preventDefault()--%>

<%--      const quesNo = $(this).data('id')--%>

<%--        $("#question-form").attr("action", "/question/edit/")--%>

<%--            await $("#modal-question").modal("show")--%>

<%--            await initEditor()--%>

<%--            $.getJSON('/question/edit/' + quesNo, function (question) {--%>

<%--                $("#quesNo").val(question.quesNo)--%>
<%--                $("#title").val(question.title)--%>
<%--                tinymce.activeEditor.setContent(question.content)--%>
<%--                $("#writer").val(question.writer)--%>

<%--            })--%>
<%--        })--%>

<%--        $("#ask-button").click(async function (e) {--%>

<%--            e.preventDefault()--%>

<%--            // 새로운 글 생성 시 quesNo 비활성화--%>
<%--            $("#quesNo").attr('disabled', true)--%>

<%--            $("#question-form").attr("action", "/question/create/")--%>

<%--            await $("#modal-question").modal("show")--%>

<%--            await initEditor()--%>

<%--        })--%>

<%--        $("#answer-button").on('click', function() {--%>

<%--            $(this).parent().parent().append("<div>질문칸 삽입</div>")--%>

<%--        })--%>
<%--    </script>--%>

<%--    <script>--%>

<%--        $("#question-submit").on('click', function(e) {--%>

<%--            e.preventDefault()--%>

<%--            // tinymce 내용 textarea에 저장--%>
<%--            tinymce.activeEditor.save()--%>

<%--            // 작성자란에 현재 로그인 유저 아이디 입력--%>
<%--            $('#writer').val('<sec:authentication property="principal.username"/>')--%>

<%--            let actionURL = $('#question-form').attr('action')--%>

<%--            let imgTags = $("#content_ifr").contents().find(".inserted-image")--%>

<%--            for (let i=0; i<Object.keys(imgTags).length - 2; i++) {--%>

<%--                const src = $(imgTags[i]).attr("src")--%>

<%--                const tag = '<input type="hidden" name="imageList[' + i + ']" value="' + src + '">'--%>
<%--                $("#question-form").append(tag)--%>
<%--            }--%>

<%--            const form = $("#question-form")--%>

<%--            // console.log(form.serialize())--%>

<%--            //ajax form submit--%>
<%--            $.ajax({--%>
<%--                type: "POST",--%>
<%--                url: actionURL,--%>
<%--                data: form.serialize(),--%>
<%--                beforeSend : function(xhr)--%>
<%--                {--%>
<%--                    xhr.setRequestHeader(header, token)--%>
<%--                },--%>
<%--                success: function(question) {--%>

<%--                    // modal window 닫기--%>
<%--                    $('.close-modal').trigger('click')--%>

<%--                    $('#question-table').prepend(--%>
<%--                    '<tr class="question-row" data-ques_no="' + question.quesNo + '">' +--%>
<%--                    '<td>' + question.quesNo + '</td>' +--%>
<%--                    '<td><a href="/question/' + question.quesNo + '" >' + question.title + '</a></td>' +--%>
<%--                    '<td>' + question.writer + '</td>' +--%>
<%--                    '<td>' + question.regDate + '</td>' +--%>
<%--                    '<td>' + question.viewCnt + '</td>' +--%>
<%--                    '<td><span id="answer-button">ANSWER</span></td></tr>'--%>
<%--                    )--%>
<%--                },--%>
<%--                error: function() {--%>
<%--                    alert("등록 실패했습니다.")--%>
<%--                }--%>
<%--            })--%>

<%--        })--%>
<%--    </script>--%>

<%--    <script>--%>
<%--            $('#more-button').on('click', function () {--%>
<%--                // alert($(location).attr('pathname'))--%>
<%--                const topic = $('#topic').data('selected-topic')--%>
<%--                const requestURL = '/topic/more/' + topic--%>
<%--                const lastQuesNo = $('.question-row').last().data('ques_no')--%>
<%--                const param = {quesNo: lastQuesNo}--%>

<%--                $.ajax({--%>
<%--                    type: 'POST',--%>

<%--                    url: requestURL,--%>

<%--                    // contentType: 'application/json; charset=utf-8',--%>
<%--                    contentType: 'application/x-www-form-urlencoded; charset=UTF-8;',--%>

<%--                    data: param,--%>

<%--                    beforeSend: function(xhr){--%>
<%--                        xhr.setRequestHeader(header, token)--%>
<%--                    },--%>

<%--                    dataType: 'json',--%>

<%--                    success: function (questions) {--%>
<%--                        for (const question of questions) {--%>
<%--                            $('#question-table').append(--%>
<%--                                '<tr class="question-row" data-ques_no="' + question.quesNo + '">' +--%>
<%--                                '<td>' + question.quesNo + '</td>' +--%>
<%--                                '<td><a href="/question/' + question.quesNo + '" >' + question.title + '</a></td>' +--%>
<%--                                '<td>' + question.writer + '</td>' +--%>
<%--                                '<td>' + question.regDate + '</td>' +--%>
<%--                                '<td>' + question.viewCnt + '</td>' +--%>
<%--                                '<td><span id="answer-button">ANSWER</span></td></tr>'--%>
<%--                            )--%>
<%--                        }--%>
<%--                    },--%>

<%--                    error: function () {--%>
<%--                    }--%>

<%--                })--%>
<%--            })--%>

<%--            $('#logout-button').on('click', function(e) {--%>

<%--                e.preventDefault()--%>

<%--                let logoutForm = $('#logout-form')--%>

<%--                logoutForm.find('#input-csrf').val(token)--%>

<%--                logoutForm.submit()--%>

<%--            })--%>



<%--    })--%>

<%--    </script>--%>
  <script>


    // $(document).ready(function() {

      $('.readMore-button').on('click', function() {
        $(this).parent().find('.fade-post').css('overflow', 'visible')
        $(this).parent().find('.fade-post').css('max-height', '100%')
        $(this).hide()
        $(this).parent().find('.overflow-fade').css('display', 'none')
      })

      function isOverflown(element) {
        return element.prop('scrollHeight') > element.height()
      }

      $('.fade-post').each(function() {
        if (isOverflown($('.fade-post'))) {
          $(this).find('.overflow-fade').css('display', 'block')
        }
      })

        // $('.search-overlay').on('click', function() {
        //   $(this).css('visibility', 'hidden')
        // })

      $('.search-input').on('keyup', (function () {
        const keyword = $(this).val()
        console.log($(this).val())
        const keywordDisplay = $('.display-keyword')

        keywordDisplay.html(keyword)

        if (keyword.length > 0) {
          $('.search-display').removeClass('hidden')

        } else {
          $('.search-display').addClass('hidden')
        }
      }))

      $('.search-input').focus(function () {
        $('.search-overlay').addClass('show-search-overlay')

      })

      $('.search-input').focusout(function () {
        $('.search-overlay').removeClass('show-search-overlay')
      })

      $('.search-button').click(function () {
        $('#search-form').submit()
      })

      $(function () {
        $('[data-toggle="popover"]').popover({trigger: "hover"})
      })

  $('.best-title:last-child').removeClass('border-bottom-grey')


    // })
  </script>
</body>



</html>
