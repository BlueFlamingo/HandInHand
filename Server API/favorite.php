<?php

require_once 'medoo.php';
require_once 'util.php';
$db = new medoo();

if ($_POST['op'] == 'showQuestionStatus') {
    echo showStatus($db, 'favorite_question', 'uid', 'qid');
} elseif ($_POST['op'] == 'showAnswerStatus') {
    echo showStatus($db, 'favorite_answer', 'uid', 'aid');
} elseif ($_POST['op'] == 'showUserStatus') {
    echo showStatus($db, 'favorite_user', 'uid', 'uid2');
} elseif ($_POST['op'] == 'toggleAnswer') {
    echo toggleAnEntry($db, 'favorite_answer', 'uid', 'aid');
} elseif ($_POST['op'] == 'toggleQuestion') {
    $status = toggleAnEntry($db, 'favorite_question', 'uid', 'qid');
    echo $status;
    $delta = -1;
    if ($status == 1) {
        $delta = 1;
    }
    $db->update('question', array('score1[+]' => $delta), array('id' => $_POST['qid']));
} elseif ($_POST['op'] == 'toggleUser') {
    echo toggleAnEntry($db, 'favorite_user', 'uid', 'uid2');
}elseif ($_POST['op'] == 'listQuestions') {
    echo listEntries($db, 'favorite_question', 'uid', 'qid');
} elseif ($_POST['op'] == 'listAnswers') {
    echo listEntries($db, 'favorite_answer', 'uid', 'aid');
} elseif ($_POST['op'] == 'listUsers') {
    echo listEntries($db, 'favorite_user', 'uid', 'uid2');
} elseif ($_POST['op'] == 'countUsers') {
  echo $db->count('favorite_user', array('uid' => $_POST['uid']));
} elseif ($_POST['op'] == 'listFollowers') {
    $entries = $db->select('favorite_user', '*', array('uid2' => $_POST['uid']));
    $ids = array();
    foreach ($entries as $ind => $entry) {
        array_push($ids, $entry['uid']);
    }
    echo json_encode($ids);
} elseif ($_POST['op'] == 'countFollowers') {
    echo $db->count('favorite_user', array('uid2' => $_POST['uid']));
}

?>