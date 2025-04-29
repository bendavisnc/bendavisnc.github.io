(defproject bdnc "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [re-frame "1.4.3"]  
                 [reagent "1.3.0" :exclusions [cljsjs/react cljsjs/react-dom cljsjs/react-dom-server]]
                 [clj-commons/secretary "1.2.4"]]
  :profiles
    {:dev
      {:dependencies [[org.clojure/clojurescript "1.10.773"]
                      [com.bhauman/figwheel-main "0.2.20"]
                     ;; optional but recommended
                      [com.bhauman/rebel-readline-cljs "0.1.4"]]
       :resource-paths ["target"]
       :clean-targets ^{:protect false} ["target"]}}
  :aliases {"fig" ["trampoline" "run" "-m" "figwheel.main"]})
