<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>GetUserMedia实例</title></head>
<body>
<video id="video" autoplay></video>
</body>
<script type="text/javascript">
    var getUserMedia = (navigator.getUserMedia || navigator.webkitGetUserMedia || navigator.mozGetUserMedia || navigator.msGetUserMedia);

    getUserMedia.call(navigator, {
        video: true,
        audio: true
    }, function (localMediaStream) {
        var video = document.getElementById('video');
        video.src = window.URL.createObjectURL(localMediaStream);
        video.onloadedmetadata = function (e) {
            console.log("Label: " + localMediaStream.label);
            console.log("AudioTracks", localMediaStream.getAudioTracks());
            console.log("VideoTracks", localMediaStream.getVideoTracks());
        };
    }, function (e) {
        console.log('Reeeejected!', e);
    });</script>
</html>