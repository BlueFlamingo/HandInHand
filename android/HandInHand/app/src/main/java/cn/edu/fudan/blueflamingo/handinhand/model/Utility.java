package cn.edu.fudan.blueflamingo.handinhand.model;

import java.util.ArrayList;

import cn.edu.fudan.blueflamingo.handinhand.middleware.AnswerHelper;
import cn.edu.fudan.blueflamingo.handinhand.middleware.CommentHelper;
import cn.edu.fudan.blueflamingo.handinhand.middleware.QuestionHelper;
import cn.edu.fudan.blueflamingo.handinhand.middleware.UserHelper;

/**
 * The type Utility.
 */
public class Utility {

    /**
     * Answer to ex answer.
     *
     * @param atemp the atemp
     * @return the ex answer
     */
    public static ExAnswer answerToExAnswer(Answer atemp) {

		if(atemp == null)
			return null;
		ExAnswer etemp = new ExAnswer();
		etemp.id = atemp.id;
		etemp.content = atemp.content;
		etemp.score1 = atemp.score1;
		etemp.score2 = atemp.score2;
		etemp.uid = atemp.uid;
		etemp.qid = atemp.qid;
		etemp.createdTime = atemp.createdTime;
		etemp. picture = atemp.picture;
		etemp.parentAid = atemp.parentAid;
		User u;
		u = new UserHelper().getByUid(atemp.uid);
		if(u == null){
			etemp.nickname = "";
			etemp.userHead = "";
		}else{
			etemp.nickname = u.nickname;
			etemp.userHead = u.portrait;
		}
		return etemp;
	}


    /**
     * Answer to ex answer.
     *
     * @param as the as
     * @return the array list
     */
    public static ArrayList<ExAnswer> answerToExAnswer(ArrayList<Answer> as) {
		ArrayList<ExAnswer> eas = new ArrayList<ExAnswer>();
		for (Answer a : as) {
			eas.add(answerToExAnswer(a));
		}
		return eas;
	}

    /**
     * Question to ex question.
     *
     * @param qtemp the qtemp
     * @return the ex question
     */
    public static ExQuestion questionToExQuestion(Question qtemp) {
		if(qtemp == null)
			return null;
		ExQuestion etemp = new ExQuestion();
		etemp.id = qtemp.id;
		etemp.content = qtemp.content;
		etemp.score1 = qtemp.score1;
		etemp.score2 = qtemp.score2;
		etemp.uid = qtemp.uid;
		etemp.createdTime = qtemp.createdTime;
		etemp.expireTime = qtemp.expireTime;
		etemp.picture =  qtemp.picture;
		etemp.title = qtemp.title;
		etemp.topics = qtemp.topics;

		User u;
		u = new UserHelper().getByUid(qtemp.uid);
		if(u == null){
			etemp.nickname = "";
			etemp.userHead = "";
		}else{
			etemp.nickname = u.nickname;
			etemp.userHead = u.portrait;
		}
		return etemp;
	}

    /**
     * Question to ex question.
     *
     * @param as the as
     * @return the array list
     */
    public static ArrayList<ExQuestion> questionToExQuestion(ArrayList<Question> as) {
		ArrayList<ExQuestion> eas = new ArrayList<ExQuestion>();
		for (Question a : as) {
			eas.add(questionToExQuestion(a));
		}
		return eas;
	}

    /**
     * Comment to ex comment.
     *
     * @param ctemp the ctemp
     * @return the ex comment
     */
    public static ExComment commentToExComment(Comment ctemp) {
		if(ctemp == null)
			return null;
		ExComment etemp = new ExComment();
		etemp.id = ctemp.id;
		etemp.uid = ctemp.uid;
		etemp.createdTime = ctemp.createdTime;
		etemp.parentCid =  ctemp.parentCid;
		etemp.content = ctemp.content;
		etemp.aid = ctemp.aid;

		User u;
		u = new UserHelper().getByUid(ctemp.uid);
		if(u == null){
			etemp.nickname = "";
			etemp.userHead = "";
		}else{
			etemp.nickname = u.nickname;
			etemp.userHead = u.portrait;
		}
		return etemp;
	}

    /**
     * Comment to ex comment.
     *
     * @param as the as
     * @return the array list
     */
    public static ArrayList<ExComment> commentToExComment(ArrayList<Comment> as) {
		ArrayList<ExComment> eas = new ArrayList<ExComment>();
		for (Comment a : as) {
			eas.add(commentToExComment(a));
		}
		return eas;
	}


    /**
     * Qidto ex question.
     *
     * @param qid the qid
     * @return the ex question
     */
    public static ExQuestion qidtoExQuestion(int qid){
		ExQuestion exq = new QuestionHelper().getByQid(qid);
		return exq;
	}

    /**
     * Qidto ex question.
     *
     * @param qlist the qlist
     * @return the array list
     */
    public static ArrayList<ExQuestion> qidtoExQuestion(ArrayList<Integer> qlist){

		ArrayList<ExQuestion> exqlist= new ArrayList<ExQuestion>();
		for (int qid : qlist) {
			exqlist.add(qidtoExQuestion(qid));
		}
		return exqlist;

	}

    /**
     * Aidto ex answer.
     *
     * @param aid the aid
     * @return the ex answer
     */
    public static ExAnswer aidtoExAnswer(int aid){
		ExAnswer exa = new AnswerHelper().getByAid(aid);
		return exa;
	}

    /**
     * Aidto ex answer.
     *
     * @param alist the alist
     * @return the array list
     */
    public static ArrayList<ExAnswer> aidtoExAnswer(ArrayList<Integer> alist){

		ArrayList<ExAnswer> exalist= new ArrayList<ExAnswer>();
		for (int aid : alist) {
			exalist.add(aidtoExAnswer(aid));
		}
		return exalist;

	}

    /**
     * Cidto ex comment.
     *
     * @param cid the cid
     * @return the ex comment
     */
    public static ExComment cidtoExComment(int cid){
		ExComment exc = new CommentHelper().getByCid(cid);
		return exc;
	}

    /**
     * Cidto ex comment.
     *
     * @param clist the clist
     * @return the array list
     */
    public static ArrayList<ExComment> cidtoExComment(ArrayList<Integer> clist){

		ArrayList<ExComment> exclist= new ArrayList<ExComment>();
		for (int cid : clist) {
			exclist.add(cidtoExComment(cid));
		}
		return exclist;

	}

    /**
     * Uidto user.
     *
     * @param uidlist the uidlist
     * @return the array list
     */
    public static ArrayList<User> uidtoUser(ArrayList<Integer> uidlist){

		ArrayList<User> ulist= new ArrayList<User>();
		for (int uid : uidlist) {
			UserHelper userhelper = new UserHelper();
			ulist.add(userhelper.getByUid(uid));
		}
		return ulist;

	}

    /**
     * Slist to objectlist.
     *
     * @param idlist the idlist
     * @return the array list
     */
    public static ArrayList< ArrayList<Object> > slistToObjectlist(ArrayList< ArrayList<Integer> > idlist){

		ArrayList< ArrayList<Object> > objlist= new ArrayList< ArrayList<Object> >();

		ArrayList<Object> exqlist= new ArrayList<Object>();
		for (int qid : idlist.get(0)) {
			exqlist.add(qidtoExQuestion(qid));
		}
		objlist.add(exqlist);

		ArrayList<Object> exalist= new ArrayList<Object>();
		for (int aid : idlist.get(1)) {
			exalist.add(aidtoExAnswer(aid));
		}
		objlist.add(exalist);

		ArrayList<Object> exclist= new ArrayList<Object>();
		for (int cid : idlist.get(2)) {
			exclist.add(cidtoExComment(cid));
		}
		objlist.add(exclist);


		return objlist;

	}

}
