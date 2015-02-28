<?php

require_once 'medoo.php';
require_once 'util.php';
$db = new medoo();

switch ($_POST['op']) {
    case 'showScore1Status':
        echo showStatus($db, 'user_answer_score1', 'uid', 'aid');
        break;
    case 'showScore2Status':
        echo showStatus($db, 'user_answer_score2', 'uid', 'aid');
        break;
    case 'toggleScore1':
        $status = toggleAnEntry($db, 'user_answer_score1', 'uid', 'aid');
        echo $status;
        $delta = -1;
        if ($status == 1) {
            $delta = 1;
        }
        $db->update('answer', array('score1[+]' => $delta), array('id' => $_POST['aid']));
        $uids = $db->select('answer', 'uid', array('id' => $_POST['aid']));
        $db->update('user', array('score1[+]' => $delta), array('id' => $uids[0]));
        break;
    case 'toggleScore2':
        $status =  toggleAnEntry($db, 'user_answer_score2', 'uid', 'aid');
        echo $status;
        $delta = -1;
        if ($status == 1) {
            $delta = 1;
        }
        $db->update('answer', array('score2[+]' => $delta), array('id' => $_POST['aid']));
        $uids = $db->select('answer', 'uid', array('id' => $_POST['aid']));
        $db->update('user', array('score2[+]' => $delta), array('id' => $uids[0]));
        break;
}


?>