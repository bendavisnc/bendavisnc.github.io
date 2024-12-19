(ns bdnc.contact)

(def github {:name "github"
             :path "https://github.com/bendavisnc"})

(def linkedin {:name "linked in"
               :path "https://www.linkedin.com/in/ben-davis-9b708548/"})

(def email {:name "email"
            :path "mailto:bendavisnc@gmail.com"})

(def links-all [github, linkedin, email])

(defn component []
  [:div#contact
   {:class ["h-dvh", "flex", "justify-center", "pt-32"]}
   [:ul {:class ["flex", "flex-col"]}
    (for [link links-all
          :let [name (:name link)
                path (:path link)
                id (str name
                        "-link")]]
      [:li {:key id}
       [:a {:id id
            :href path}
        name]])]])
