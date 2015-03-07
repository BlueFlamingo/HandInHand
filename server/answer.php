<?php

require_once 'medoo.php';
$db = new medoo();

if ($_POST['op'] == 'add') {
    $entry = json_decode($_POST['entry'], true);
    unset($entry['id']);
    echo $db->insert('answer', $entry);
} elseif ($_POST['op'] == 'delete') {
    echo $db->delete('answer', array('id' => $_POST['aid']));
} elseif ($_POST['op'] == 'update') {
    $entry = json_decode($_POST['entry'], true);
    echo $db->update('answer', $entry, array('id' => $entry['id']));
} elseif ($_POST['op'] == 'getByQid') {
    $entries = $db->select('answer', '*', array('qid' => $_POST['qid'],
        'ORDER' => 'createdTime DESC'));
    echo json_encode($entries);
} elseif ($_POST['op'] == 'getByAid') {
    $entries = $db->select('answer', '*', array('id' => $_POST['aid']));
    echo json_encode($entries[0]);
}

?>