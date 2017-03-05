(ns wonderblog.style.mobile.banner
  (:require 
    [wonderblog.style.mobile.constants :as constants]
    [wonderblog.style.standard.banner :as standard-banner]
    [wonderblog.util :refer [hmerge]]
    )
  )

(def style* 
  [".banner"
    {
    }
    ["a.sitelink"
      {
        "left" "1vw"
        "bottom" "1vh"
      }
    ]
    ["a.navlink-0"
      {
        "left" "60vw"
      }
    ]
    ["a.navlink-1"
      {
        "left" "70vw"
      }
    ]
    ["a.navlink-2"
      {
        "left" "80vw"
      }
    ]
  ])

(def style
  (hmerge
    (eval standard-banner/style*)
    style*))
