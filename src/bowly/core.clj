(ns bowly.core
  (:gen-class))

(require '[irclj.core :as irc])
(require '[clojure.string  :as string])

(defn echo [s] s)

(def commands {
               ";echo" echo
               })

(defn trim-command [s]
  (string/join " " (rest (string/split s #"\s"))))

(defn append-sender [msg s]
  (string/join " " [(str (:nick msg) ":") s]))

(defn parse-command [s]
   (first (string/split s #"\s")))

(defn privmsg [irc msg & s]
  "General reply to what's going on. Execute commands."
  (let [command (parse-command (:text msg))]
     (irc/reply irc
               msg
               ((get commands command (fn [& args]))
               (append-sender msg (trim-command (:text msg)))))))

(defn -main
  "Connect and start listening"
  [& args]
  ((def connection (irc/connect "irc.freenode.net" 6667 "Bowly"
                                :callbacks {
                                            :privmsg privmsg
                                            }))
   (irc/join connection "#hahasame")))
