(ns bdnc.navigation
  (:require
   [bdnc.links :as links]
   [bdnc.pages :as pages]
   [goog.string :as gstring]
   [goog.string.format]))

(defn component [props]
  [:div#navigation
   props
   [:ul {:class ["flex", "flex-col", "gap-8", "md:gap-2"]}
    (for [page-id (rest (keys pages/all))
          ;; `rest` because skip navigation itself
          :when (not (:title-hidden (page-id pages/all)))
          :let [link-id page-id
                link (link-id links/all)
                _ (when-not link
                    (.error js/console (gstring/format "No link for `%s`."
                                                       link-id)))
                link-name (:name link)
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
