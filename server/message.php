<?php

require_once 'medoo.php';
$db = new medoo();

switch ($_POST['op']) {
    case 'send':
        $entry = json_decode($_POST['entry'], true);
        unset($entry['id']);
        echo $db->insert('message', $entry);
        break;
    case 'getSentMsgs':
        $entries = $db->select('message', '*', array('sender' => $_POST['uid'],
            'ORDER' => 'createdTime DESC'));
        echo json_encode($entries);
        break;
    case 'getAllReceivedMsgs':
        $entries = $db->select('message', '*', array('receiver' => $_POST['uid'],
            'ORDER' => 'createdTime DESC'));
        echo json_encode($entries);
        break;
    case 'getUnhandledMsgs':
        $entries = $db->select('message', '*', array('AND' =>
            array('receiver' => $_POST['uid'],
            'status' => 0), 'ORDER' => 'createdTime DESC'));
        echo json_encode($entries);
        break;
}

?>