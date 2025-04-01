(ns bdnc.pages.experience.base
  (:require
   [bdnc.helpers]
   [bdnc.pages.experience.buttons :as buttons]
   [bdnc.pages.experience.dispatches :as dispatches]
   [bdnc.progress-circles :as progress-circles]
   [goog.string :as gstring]
   [goog.string.format]
   [re-frame.core :as reframe]))

(def css-by-state {:one-and-only {:class ["grow", "shrink-0", "h-[100%]"]}
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

(defn main-section [props, is-active?, {:keys [name, title, logo]}]
  [:div.main-section props
   [:div {:class ["flex", "flex-col"]}
    [:div {:class ["portrait:md:text-[4rem]", "text-[#f9eac4]", "2xl:text-[#3fc7b5]", "text-[2rem]", "2xl:text-[4rem]"]}
     name]
    [:div {:class ["font-light", "portrait:md:text-[1.5rem]", "2xl:text-[2rem]"]}
     title]
    [buttons/expand-button
     {:class ["w-[1rem]", "h-auto", "md:w-[1.5rem]"]}
     is-active?]]
   [:div {:class ["flex", "flex-col", "justify-center"]}
    [:div {:class ["w-[4rem]", "2xl:w-[8rem]", "h-auto", "portrait:md:w-[8rem]", "fill-slate-600"]}
     logo]]])

(defn invoke-details-scroll-fn [target-container-ref, company, target-index]
  (fn []
    (println (gstring/format "Scrolling to new (%s) details item (index, `%s`)" company, target-index))
    (let [target (aget (.-children @target-container-ref)
                   target-index)]
      (println (gstring/format "Scrolling to detail item `%s`." (.-id target)))
      (.scrollTo @target-container-ref (clj->js {:left (.-offsetLeft target)
                                                 :top 0
                                                 :behavior "smooth"})))))

(defn details-section [props, company, details]
  (let [item-detail-id-active @(reframe/subscribe [:experience/item-detail-active company])
        target-container-ref (clojure.core/atom nil)]
    [:div.details-section props
     [buttons/previous-button {:class ["details-previous-button" "w-[1rem]", "2xl:w-[2rem]", "h-auto", "stroke-white", "stroke-[0.25rem]", "2xl:stroke-[0.25rem]"]
                               :on-click (invoke-details-scroll-fn target-container-ref company (max (dec item-detail-id-active)
                                                                                                  0))}]
     [:ol.details {:id (str (name company) "-details")
                   :class ["flex", "mt-[4%]", "overflow-auto", "snap-mandatory", "snap-x", "w-[78vw]", "2xl:w-[47vw]"]
                   :ref (fn [target]
                          (when target
                            (reset! target-container-ref target)
                            (dispatches/details-scroll! target company)))}
      (for [[i, detail] (map-indexed vector details)
            :let [id (gstring/format "detail-item-%s-%i"
                                      (name company)
                                      i)]]
        [:li {:key id
              :id id
              :class ["shrink-0", "snap-start", "w-[78vw]", "2xl:w-[47vw]"]
              :data-i i}
         [:span {:class ["inline-block"]}
          detail]])]
     [buttons/next-button {:class ["details-next-button" "w-[1rem]", "2xl:w-[2rem]", "h-auto", "stroke-white", "stroke-[0.25rem]", "2xl:stroke-[0.25rem]"]
                           :on-click (invoke-details-scroll-fn target-container-ref company (min (inc item-detail-id-active)
                                                                                              (dec (count details))))}]]))

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
                  (if item-translation
                    {:style {:transform (gstring/format "translateY(-%spx)" item-translation)}}
                    {}))
            [main-section {:id id
                           :class ["flex", "font-bold" "justify-between", "w-[20rem]", "portrait:md:w-[40rem]", "2xl:w-[42rem]"]
                           :on-click (dispatches/expand-click company (if is-active? nil company))}
                          is-active?
                          item]
            [details-section {:id (str (name id) "-details")
                              :class (concat ["grow", "2xl:grow-[0.5]", "flex", "shrink-0", "font-bold",  "2xl:font-normal", "overflow-scroll", "portrait:md:text-[1.5rem]", "text-[#f9eac4]", "2xl:text-slate-600", "w-dvw", "2xl:w-[60vw]", "justify-around", "2xl:text-[2rem]"]
                                       (if is-active?
                                         []
                                         ["invisible", "h-0"]))}
                             company
                             details]
            [progress-circles/component {:id (str (name id)
                                                  "-progress-circles")
                                         :class (concat ["w-[12rem]"]
                                                        (if is-active?
                                                          ["h-auto"]
                                                          ["invisible", "h-0"]))}
                                        (count details)
                                        [:experience/item-detail-active company]
                                        {:class ["fill-[#f9eac4]", "2xl:fill-[#3fc7b5]"]}
                                        {:class ["fill-white", "opacity-[0.67]"]}]]))])

(defn experience-items [props, component-id, items]
  [:f>
   (fn []
     (let [_ @(reframe/subscribe [:dimensions])]
       [:ul (merge props {:ref (fn [target]
                                 (if (nil? target)
                                   (println (gstring/format "`ul` not found for `%s`."
                                                            (name component-id)))
                                   (init-top-positions-memo! target)))})
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
     [experience-items {:class ["absolute", "bottom-[39%]", "2xl:bottom-[6%]", "flex", "flex-col", "gap-[1.5rem]", "h-[48vh]", "2xl:h-[70vh]", "pt-[8vh]", "portrait:md:pt-0", "md:pt-[5.5vh]"]}
                       id
                       content-all]]))
