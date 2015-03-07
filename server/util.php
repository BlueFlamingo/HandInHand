<?php

function deDuplicate($ids) {
    $uniqueIds = array();
    foreach($ids as $ind1 => $id1) {
        $isDuplicate = false;
        foreach($uniqueIds as $ind2 => $id2) {
            if ($id1 == $id2) {
                $isDuplicate = true;
                break;
            }
        }
        if (!$isDuplicate) {
            array_push($uniqueIds, $id1);
        }
    }
    return $uniqueIds;
}

function filterQuestionsExpired(&$db) {
    $qids = $db->select('question', 'id', array('AND' => array(
        'expireTime[<]' => 1000 * time(),
        'expireTime[!]' => 0
    )));
    foreach($qids as $ind => $qid) {
        $db->delete('question_topic', array('qid' => $qid));
        $db->insert('question_topic', array('qid' => $qid,
            'tid' => '-1'));
    }
}

function showStatus(&$db, $table, $id1, $id2) {
    $status = $db->count($table, array('AND' =>
            array($id1 => $_POST[$id1],
                $id2 => $_POST[$id2])));
    return $status;
}

function toggleAnEntry(&$db, $table, $id1, $id2) {
    $curState = $db->count($table, array('AND' =>
        array($id1 => $_POST[$id1],
            $id2 => $_POST[$id2])));
    if ($curState == 0) {
        $db->insert($table, array($id1 => $_POST[$id1],
            $id2 => $_POST[$id2]));
        return 1;
    } else {
        $db->delete($table, array('AND' =>
            array($id1 => $_POST[$id1],
                $id2 => $_POST[$id2])));
        return 0;
    }
}

function listEntries(&$db, $table, $id1, $id2) {
    $entries = $db->select($table, '*', array($id1 => $_POST[$id1]));
    $ids = array();
    foreach ($entries as $ind => $entry) {
        array_push($ids, $entry[$id2]);
    }
    return json_encode($ids);
}



?>