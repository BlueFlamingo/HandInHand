<?php

require_once 'medoo.php';
require_once 'question.php';
$db = new medoo();

if ($_POST['op'] == 'register') {
    $entry = json_decode($_POST['entry'], true);
    if ($db->count('user', array('username' => $entry['username'])) > 0) {
        echo -1;
        return;
    }
    unset($entry['id']);
    echo $db->insert('user', $entry);
} elseif ($_POST['op'] == 'update') {
    $entry = json_decode($_POST['entry'], true);
    echo ($db->update('user', $entry, array('id' => $entry['id'])));
} elseif ($_POST['op'] == 'count') {
    echo $db->count('user', array('username' => $_POST['username']));
} elseif ($_POST['op'] == 'authenticate') {
    echo $db->count('user',
        array('AND' =>
            array('username' => $_POST['username'],
                'password' => $_POST['password'])));
} elseif ($_POST['op'] == 'get') {
    $data = $db->select('user', '*', array('username' => $_POST['username']));
    echo json_encode($data[0]);
} elseif ($_POST['op'] == 'countQuestions') {
    echo $db->count('question', array('uid' => $_POST['uid']));
} elseif ($_POST['op'] == 'getQuestions') {
    $qids = $db->select('question', array('id'), array('uid' => $_POST['uid'],
        'ORDER' => 'createdTime DESC'));
    $entries = array();
    foreach ($qids as $ind => $qid) {
        array_push($entries, getByQid($qid['id']));
    }
    echo json_encode($entries);
} elseif ($_POST['op'] == 'countAnswers') {
    echo $db->count('answer', array('uid' => $_POST['uid']));
} elseif ($_POST['op'] == 'getAnswers') {
    $entries = $db->select('answer', '*', array('uid' => $_POST['uid'],
        'ORDER => createdTime'));
    echo json_encode($entries);
} elseif ($_POST['op'] == 'getByUid') {
    $entries = $db->select('user', '*', array('id' => $_POST['uid']));
    echo json_encode($entries[0]);
}

?>