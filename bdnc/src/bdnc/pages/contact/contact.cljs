(ns bdnc.pages.contact.contact
  (:require
   [bdnc.pages.contact.svgs :refer [email-logo github-logo linkedin-logo resume-logo]]
   [goog.string :as gstring]
   [goog.string.format]))

(def github {:name "github"
             :path "https://github.com/bendavisnc"
             :logo github-logo})

(def linkedin {:name "linked in"
               :path "https://www.linkedin.com/in/ben-davis-9b708548/"
               :logo linkedin-logo})

(def email {:name "email"
            :path "mailto:bendavisnc@gmail.com"
            :logo email-logo})

(def resume {:name "resume"
             :path "todo"
             :logo resume-logo})

(def links-all {:github github
                :linkedin linkedin
                :email email
                :resume resume})

(defn component [props]
  [:div#contact
   props
   [:ul {:class ["absolute", "bottom-[39%]", "flex", "flex-col", "gap-8", "md:h-[12vh]", "md:gap-[8vh]", "pt-[8vh]"]}
    (for [[link-id, link] links-all
          :let [link-name (:name link)
                path (:path link)
                id (gstring/format "%s-link" (name link-id))
                logo (:logo link)]]
      [:li {:key id}
       [:a {:id id
            :href path}
        [:div {:class ["flex", "items-center", "text-5xl", "text-[#f9eac4]", "font-black", "gap-1.5"]}
         [:div {:class ["fill-slate-600"]}
          logo]
         [:span {:class []}
          link-name]]]])]])
