<?php

require_once 'medoo.php';
require_once 'util.php';
$db = new medoo();
filterQuestionsExpired($db);

function getByQid($qid) {
    $db = new medoo();
    $question = $db->select('question', '*', array('id' => $qid));
    $question = $question[0];
    $topics = $db->select('question_topic', array('tid'),
            array('qid' => $question['id']));
    $tids = array();
    foreach ($topics as $ind => $topic) {
        array_push($tids, $topic['tid']);
    }
    $question['topics'] = $tids;
    $user = $db->select('user', '*', array('id' => $question['uid']));
    $user = $user[0];
    $question['nickname'] = $user['nickname'];
    $question['userHead'] = $user['portrait'];
    return $question;
}

if ($_POST['op'] == 'add') {
    $entry = json_decode($_POST['entry'], true);
    $topics = $entry['topics'];
    unset($entry['id']);
    unset($entry['topics']);
    $qid = $db->insert('question', $entry);
    foreach ($topics as $ind => $tid) {
        $db->insert('question_topic', array('qid' => $qid,
            'tid' => $tid));
    }
    echo $qid;
} elseif ($_POST['op'] == 'update') {
    $entry = json_decode($_POST['entry'], true);
    $topics = $entry['topics'];
    $db->delete('question_topic', array('qid' => $entry['id']));
    foreach ($topics as $ind => $tid) {
        $db->insert('question_topic', array('qid' => $entry['id'],
            'tid' => $tid));
    }
    unset($entry['topics']);
    echo $db->update('question', $entry, array('id' => $entry['id']));
} elseif ($_POST['op'] == 'delete') {
    echo $db->delete('question', array('id' => $_POST['qid']));
} elseif ($_POST['op'] == 'getByTopic') {
    $qids = $db->select('question', array('[>]question_topic' =>
        array('id' => 'qid')), array('question.id'),
        array('question_topic.tid' => $_POST['tid'],
            'ORDER' => 'createdTime DESC'));
    $questions = array();
    foreach ($qids as $ind => $qid) {
        array_push($questions, getByQid($qid['id']));
    }
    echo json_encode($questions);
} elseif ($_POST['op'] == 'getByQid') {
    echo json_encode(getByQid($_POST['qid']));
} elseif ($_POST['op'] == 'getHotest') {
    $qids = $db->select('question', array('[>]question_topic' =>
        array('id' => 'qid')), array('question.id'),
        array('question_topic.tid[!]' => -1,
            'ORDER' => 'score1 DESC',
            'LIMIT' => 20));
    $qids = deDuplicate($qids);
    $questions = array();
    foreach ($qids as $ind => $qid) {
        array_push($questions, getByQid($qid['id']));
    }
    echo json_encode($questions);
} elseif ($_POST['op'] == 'getLatest') {
    $qids = $db->select('question', array('[>]question_topic' =>
        array('id' => 'qid')), array('question.id'),
        array('question_topic.tid[!]' => -1,
            'ORDER' => 'createdTime DESC',
            'LIMIT' => $_POST['count']));
    $qids = deDuplicate($qids);
    $questions = array();
    foreach ($qids as $ind => $qid) {
        array_push($questions, getByQid($qid['id']));
    }
    echo json_encode($questions);
}

?>