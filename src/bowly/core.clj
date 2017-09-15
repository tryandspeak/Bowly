(ns bowly.core
  (:gen-class))

(load "config")

(require '[irclj.core :as irc])
(require '[clojure.string :as string])

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
  "Receive messages. Execute commands."
  (let [command (parse-command (:text msg))]
     (irc/reply irc
               msg
               ((get commands command (fn [& args]))
               (append-sender msg (trim-command (:text msg)))))))

(defn -main
  "Connect and start listening"
  [& args]
  ((def connection (irc/connect (:server config) (:port config) (:nick config)
                                :callbacks {
                                            :privmsg privmsg
                                            }))
   (irc/join connection (:channel config))))
