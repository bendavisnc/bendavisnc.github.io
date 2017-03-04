(ns wonderblog.style.mobile.banner
  (:require 
    [wonderblog.style.mobile.constants :as constants]
    [wonderblog.style.standard.banner :as standard-banner]
    )
  )

; (defmacro style* []
	; `~standard-banner/style)

; (def style 
	; (style*))

(def style
	(eval standard-banner/style*))
