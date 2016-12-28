package cn.karent.nanhang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import cn.karent.nanhang.model.Score;
import cn.karent.nanhang.R;

/**
 * Created by wan on 2016/12/26.
 * 成绩的Adapter
 */
public class ScoreAdapter extends ArrayAdapter<Score> {

    private Context mContext;

    private int mResouceId;

    public ScoreAdapter(Context context, int resourceId) {
        super(context, resourceId);
        mContext = context;
        mResouceId = resourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHold hold = null;
        if( convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(mResouceId, null);
            hold = new ViewHold();
            hold.setName((TextView)convertView.findViewById(R.id.scoreItem_name));
            hold.setCourseTime((TextView)convertView.findViewById(R.id.scoreItem_courseTime));
            hold.setCoursePropety((TextView)convertView.findViewById(R.id.scoreItem_courseProperty));
            hold.setProperty((TextView)convertView.findViewById(R.id.scoreItem_property));
            hold.setWay((TextView)convertView.findViewById(R.id.scoreItem_way));
            hold.setScore((TextView)convertView.findViewById(R.id.scoreItem_score));
            hold.setCredit((TextView)convertView.findViewById(R.id.scoreItem_credit));
            convertView.setTag(hold);
        } else {
            hold = (ViewHold)convertView.getTag();
        }
        Score s = getItem(position);
        hold.getName().setText(s.getName());
        hold.getCourseTime().setText(s.getCourseTime());
        hold.getCoursePropety().setText(s.getCourseProperty());
        hold.getProperty().setText(s.getProperty());
        hold.getWay().setText(s.getWay());
        hold.getScore().setText(s.getScore() + "分");
        hold.getCredit().setText(s.getCredit() + "");
        return convertView;
    }

    class ViewHold {
        /**
         * 课时
         */
        private TextView courseTime;
        /**
         * 课程名称
         */
        private TextView name;
        /**
         * 课程性质，比如：全校任选课
         */
        private TextView coursePropety;
        /**
         * 选修还是必修
         */
        private TextView property;
        /**
         * 考试方式
         */
        private TextView way;
        /**
         * 分数
         */
        private TextView score;
        /**
         * 学分
         */
        private TextView credit;

        public TextView getCourseTime() {
            return courseTime;
        }

        public void setCourseTime(TextView courseTime) {
            this.courseTime = courseTime;
        }

        public TextView getName() {
            return name;
        }

        public void setName(TextView name) {
            this.name = name;
        }

        public TextView getCoursePropety() {
            return coursePropety;
        }

        public void setCoursePropety(TextView coursePropety) {
            this.coursePropety = coursePropety;
        }

        public TextView getProperty() {
            return property;
        }

        public void setProperty(TextView property) {
            this.property = property;
        }

        public TextView getWay() {
            return way;
        }

        public void setWay(TextView way) {
            this.way = way;
        }

        public TextView getScore() {
            return score;
        }

        public void setScore(TextView score) {
            this.score = score;
        }

        public TextView getCredit() {
            return credit;
        }

        public void setCredit(TextView credit) {
            this.credit = credit;
        }
    }

}
