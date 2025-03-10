(ns bdnc.pages.experience.base
  (:require
   [bdnc.helpers]
   [bdnc.pages.experience.buttons :as buttons]
   [bdnc.pages.experience.circles :as circles]
   [bdnc.pages.experience.dispatches :as dispatches]
   [goog.string :as gstring]
   [goog.string.format]
   [re-frame.core :as reframe]))

(def css-by-state {:one-and-only {}
                   :together {}
                   :vanished {:class ["scale-0"]}})

(defn init-top-positions-memo! [target]
  (let [elements (.-children target)
        y-topmost (.-top (.getBoundingClientRect (first elements)))]
    (doseq [child elements]
      (let [y (- (.-top (.getBoundingClientRect child))
                 y-topmost)
            company (keyword (.getAttribute child "data-company"))]
        (reframe/dispatch [:experience/item-translation company y])))))

(defn main-section [props, is-active?, onclick, {:keys [name, title, logo]}]
  [:div.main-section props
   [:div {:class ["flex", "flex-col"]}
    [:div {:class ["portrait:md:text-[4rem]", "text-[#f9eac4]", "text-[2rem]"]}
     name]
    [:div {:class ["font-light", "portrait:md:text-[1.5rem]"]}
     title]
    [buttons/expand-button
     {:class ["w-[1rem]", "h-auto", "md:w-[1.5rem]"]
      :on-click onclick}
     is-active?]]
   [:div {:class ["flex", "flex-col", "justify-center"]}
    [:div {:class ["w-[4rem]", "h-auto", "portrait:md:w-[8rem]", "fill-slate-600"]}
     logo]]])

(defn details-section [props, company, details]
  [:div.details-section props
   [buttons/previous-button {:class ["details-previous-button" "w-[1rem]", "h-auto", "stroke-white", "stroke-[0.25rem]"]
                             :on-click dispatches/previous-detail-click}]
   [:ol.details {:id (str (name company) "-details")
                 :class ["flex", "mt-[4%]", "overflow-auto", "snap-mandatory", "snap-x", "w-[40rem]"]
                 :ref (fn [target]
                        (when target
                          (dispatches/details-scroll! target company)))}
    (for [[i, detail] (map-indexed vector details)
          :let [id (gstring/format "detail-item-%s-%i"
                                    (name company)
                                    i)]]
      [:li {:key id
            :id id
            :class ["shrink-0", "snap-start", "w-[40rem]"]
            :data-i i}
       [:span {:class ["inline-block"]}
        detail]])]
   [buttons/next-button {:class ["details-next-button" "w-[1rem]", "h-auto", "stroke-white", "stroke-[0.25rem]"]
                         :on-click dispatches/next-detail-click}]])

;; Contains everything under a single company experience, eg `comcast`.
(defn experience-item [props, id, company, item]
  [:f> (fn []
         (let [item-id-active @(reframe/subscribe [:experience/item-active])
               is-active? (= company item-id-active)
               item-translation @(reframe/subscribe [:experience/item-translation company])
               details (:details item)
               state-key (cond (nil? item-id-active) :together
                               is-active? :one-and-only
                               :else :vanished)]
           [:li (merge-with
                  into
                  props
                  {:data-company company}
                  (state-key css-by-state)
                  (or (some->> item-translation
                               (gstring/format "translateY(-%spx)" item-translation)
                               (assoc-in {} [:style :transform]))
                      {}))
            [main-section {:id id
                           :class ["flex", "font-bold" "justify-between", "w-[20rem]", "portrait:md:w-[40rem]"]}
                          is-active?
                          (dispatches/expand-click company (if is-active? nil company))
                          item]
            [details-section {:id (str (name id) "-details")
                              :class (concat ["flex", "font-bold", "overflow-scroll", "portrait:md:text-[1.5rem]", "text-[#f9eac4]", "w-dvw", "justify-around"]
                                       (if is-active?
                                         ["h-48"]
                                         ["invisible", "h-0"]))}
                             company
                             details]
            [circles/circles {:id (str (name id)
                                    "-circles")
                              :class (concat ["w-[12rem]"]
                                       (if is-active?
                                         ["h-auto"]
                                         ["invisible", "h-0"]))}
                             (count details)
                             company]]))])

(defn experience-items [props, component-id, items]
  [:f>
   (fn []
     (let [top-position-memo @(reframe/subscribe [:experience/top-position-memo])]
       [:ul (merge props {:ref (fn [target]
                                 (cond (nil? target) (println (gstring/format "`ul` not found for `%s`."
                                                                              (name component-id)))
                                       (nil? top-position-memo) (init-top-positions-memo! target)))})
        (for [[item-id, item] items
              :let [item-id-full (keyword (gstring/format "%s-%s" (name component-id)
                                                                  (name item-id)))]]
          [experience-item {:key item-id-full
                            :id item-id-full
                            :class ["duration-1000", "flex", "flex-col", "items-center", "transition"]}
                           item-id-full
                           item-id
                           item])]))])

(defn component* [id, content-all]
  (fn [props]
    [:div (conj props
                {:id id})
     [experience-items {:class ["flex", "flex-col", "absolute", "bottom-[39%]", "pt-[20%]", "h-[50%]", "gap-[1.5rem]"]}
                       id
                       content-all]]))
