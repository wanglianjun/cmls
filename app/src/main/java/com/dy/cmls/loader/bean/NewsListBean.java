package com.dy.cmls.loader.bean;

import java.util.List;

/**
 * Created by lcjing on 2019/1/3.
 */

public class NewsListBean {

    /**
     * status : 9999
     * message : 请求成功
     * info : {"now_page":1,"all_page":1,"article":[{"id":"27","title":"系统公告","time":"1545725779","content":"<p>系统公告系统公告系统公告系统公告系统公告<\/p>"},{"id":"26","title":"测试","time":"1541034447","content":"测试"},{"id":"13","title":"得到","time":"1540550162","content":"<p>胜多负少<\/p>"},{"id":"12","title":"发的福","time":"1540550132","content":"<p>基督教<\/p>"}]}
     */

    private String status;
    private String message;
    private InfoBean info;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public static class InfoBean {
        /**
         * now_page : 1
         * all_page : 1
         * article : [{"id":"27","title":"系统公告","time":"1545725779","content":"<p>系统公告系统公告系统公告系统公告系统公告<\/p>"},{"id":"26","title":"测试","time":"1541034447","content":"测试"},{"id":"13","title":"得到","time":"1540550162","content":"<p>胜多负少<\/p>"},{"id":"12","title":"发的福","time":"1540550132","content":"<p>基督教<\/p>"}]
         */

        private int now_page;
        private int all_page;
        private List<ArticleBean> article;

        public int getNow_page() {
            return now_page;
        }

        public void setNow_page(int now_page) {
            this.now_page = now_page;
        }

        public int getAll_page() {
            return all_page;
        }

        public void setAll_page(int all_page) {
            this.all_page = all_page;
        }

        public List<ArticleBean> getArticle() {
            return article;
        }

        public void setArticle(List<ArticleBean> article) {
            this.article = article;
        }

        public static class ArticleBean {
            /**
             * id : 27
             * title : 系统公告
             * time : 1545725779
             * content : <p>系统公告系统公告系统公告系统公告系统公告</p>
             */

            private String id;
            private String title;
            private String time;
            private String content;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }
        }
    }
}
