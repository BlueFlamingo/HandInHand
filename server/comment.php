<?php
require_once 'medoo.php';
$db = new medoo();

if ($_POST['op'] == 'add') {
    $entry = json_decode($_POST['entry'], true);
    unset($entry['id']);
    echo $db->insert('comment', $entry);
} elseif ($_POST['op'] == 'delete') {
    echo $db->delete('comment', array('id' => $_POST['cid']));
} elseif ($_POST['op'] == 'update') {
    $entry = json_decode($_POST['entry'], true);
    echo $db->update('comment', $entry, array('id' => $entry['id']));
} elseif ($_POST['op'] == 'getByAid') {
    $entries = $db->select('comment', '*', array('aid' => $_POST['aid'],
        'ORDER' => 'createdTime'));
    echo json_encode($entries);
} elseif ($_POST['op'] == 'getByCid') {
    $entries = $db->select('comment', '*', array('id' => $_POST['cid']));
    echo json_encode($entries[0]);
} elseif ($_POST['op'] == 'countComments') {
    echo $db->count('comment', array('aid' => $_POST['aid']));
}
?>