(defproject bowly "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [
                 [org.clojure/clojure "1.8.0"]
                 [irclj "0.5.0-alpha4"]
                 ]
  :main ^:skip-aot bowly.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
