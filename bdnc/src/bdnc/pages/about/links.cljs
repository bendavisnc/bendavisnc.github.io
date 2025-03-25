(ns bdnc.pages.about.links
  (:require
   [bdnc.pages.contact.svgs :as svgs]))

(def github {:name "github"
             :path "https://github.com/bendavisnc"
             :logo svgs/github-logo})

(def linkedin {:name "linked in"
               :path "https://www.linkedin.com/in/ben-davis-9b708548/"
               :logo svgs/linkedin-logo})

(def email {:name "email"
            :path "mailto:bendavisnc@gmail.com"
            :logo svgs/email-logo})

(def resume {:name "resume"
             :path "todo"
             :logo svgs/resume-logo})

(def links {:github github
            :linkedin linkedin
            :email email
            :resume resume})
