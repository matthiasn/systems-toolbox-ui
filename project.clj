(defproject matthiasn/systems-toolbox-ui "0.6.2"
  :description "UI components for systems-toolbox"
  :url "https://github.com/matthiasn/systems-toolbox"
  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}

  :source-paths ["src/cljc" "src/clj" "src/cljs"]

  :dependencies [[reagent "0.6.0"]
                 [matthiasn/systems-toolbox "0.6.4"]]

  :profiles
  {:dev {:dependencies   [[org.clojure/clojure "1.9.0-alpha14"]
                           [org.clojure/clojurescript "1.9.293"]
                           [matthiasn/systems-toolbox-sente "0.6.5"]
                           [ch.qos.logback/logback-classic "1.1.8"]
                           [hiccup "1.0.5"]
                           [org.seleniumhq.selenium/selenium-java "3.0.1"]
                           [org.seleniumhq.selenium/selenium-api "3.0.1"]
                           [org.seleniumhq.selenium/selenium-server "3.0.1"]
                           [org.seleniumhq.selenium/selenium-remote-driver "3.0.1"]
                           [org.seleniumhq.selenium/selenium-chrome-driver "3.0.1"]
                           [com.codeborne/phantomjsdriver "1.3.0"
                            :exclusions [org.apache.httpcomponents/httpcore]]
                           [clj-webdriver "0.7.2"
                            :exclusions [org.clojure/core.cache
                                         org.apache.httpcomponents/httpcore
                                         commons-io]]]
          :resource-paths ["test-resources"]}
   :test {:resource-paths ["test-resources"]
          :jvm-opts       ["-Dwebdriver.chrome.driver=bin/chromedriver"
                           "-Dwebdriver.gecko.driver=bin/geckodriver"]}}

  :plugins [[lein-codox "0.10.2" :exclusions [org.clojure/clojure]]
            [test2junit "1.2.5"]
            [lein-cljsbuild "1.1.5"]]

  :test2junit-output-dir ~(or (System/getenv "CIRCLE_TEST_REPORTS") "target/test2junit")

  :clean-targets ^{:protect false} ["test-resources/public/js/build/" "target/"]

  :aliases {"integration-tests" ["do" "clean" ["cljsbuild" "once" "test"] "test2junit"]}

  :cljsbuild
  {:builds [{:id           "test"
             :source-paths ["src/cljs" "test/cljs/"]
             :figwheel     true
             :compiler     {:main          "example.core"
                            :asset-path    "js/build"
                            :optimizations :advanced
                            :output-to     "test-resources/public/js/build/example.js"}}]})
