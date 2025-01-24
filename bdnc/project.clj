(defproject bdnc "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [cljsjs/react "18.3.1-1"]
                 [cljsjs/react-dom "18.3.1-1"]
                 [re-frame "1.4.3"]  
                 [reagent "0.10.0" :exclusions [cljsjs/react cljsjs/react-dom cljsjs/react-dom-server]]]
           
  :profiles
    {:dev
      {:dependencies [[org.clojure/clojurescript "1.10.773"]
                      [com.bhauman/figwheel-main "0.2.20"]
                     ;; optional but recommended
                      [com.bhauman/rebel-readline-cljs "0.1.4"]]
       :resource-paths ["target"]
       :clean-targets ^{:protect false} ["target"]}}
  :aliases {"fig" ["trampoline" "run" "-m" "figwheel.main"]})
