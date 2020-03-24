function setCourseID(courseID) {
    document.getElementById("courseID").value = courseID;
}

function setExamExplanation(examID, title, description, duration) {
    document.getElementById("examID").value = examID;
    document.getElementById("editTitle").value = title;
    document.getElementById("editDescription").value = description;
    document.getElementById("editDuration").value = duration;
}

function setExamID(examID) {
    document.getElementById("addDescriptiveQuestionExamID").value = examID;
    document.getElementById("multipleChoiceQuestionExamID").value = examID;
}

