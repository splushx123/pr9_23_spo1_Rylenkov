import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class Event {
    private String type;
    private LocalDate date;
    private List<String> participants;
    private String result;

    public Event(String type, LocalDate date) {
        this.type = type;
        this.date = date;
        this.participants = new ArrayList<>();
        this.result = "Не обработано";
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        if (type == null || type.trim().isEmpty()) {
            throw new IllegalArgumentException("Тип события не может быть пустым");
        }
        this.type = type;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        if (date == null) {
            throw new IllegalArgumentException("Дата не может быть null");
        }
        this.date = date;
    }

    public List<String> getParticipants() {
        return new ArrayList<>(participants);
    }

    public void setParticipants(List<String> participants) {
        if (participants == null) {
            throw new IllegalArgumentException("Список участников не может быть null");
        }
        this.participants = new ArrayList<>(participants);
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        if (result == null || result.trim().isEmpty()) {
            throw new IllegalArgumentException("Результат не может быть пустым");
        }
        this.result = result;
    }

    public void addParticipant(String participant) {
        if (participant == null || participant.trim().isEmpty()) {
            throw new IllegalArgumentException("Участник не может быть пустым");
        }
        participants.add(participant);
    }

    public String getEventInfo() {
        return String.format("Событие: %s, Дата: %s, Участников: %d, Результат: %s",
                type, date, participants.size(), result);
    }

    public boolean isProcessed() {
        return !"Не обработано".equals(result);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Event event = (Event) obj;
        return type.equals(event.type) &&
                date.equals(event.date) &&
                result.equals(event.result);
    }

    @Override
    public String toString() {
        return getEventInfo();
    }
}

class BusinessMeeting extends Event {
    private String agenda;
    private int duration;

    public BusinessMeeting(LocalDate date, String agenda, int duration) {
        super("Деловая встреча", date);
        this.agenda = agenda;
        this.duration = duration;
    }

    public String getAgenda() {
        return agenda;
    }

    public void setAgenda(String agenda) {
        this.agenda = agenda;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return String.format("%s, Повестка: %s, Длительность: %d мин",
                super.toString(), agenda, duration);
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;
        BusinessMeeting other = (BusinessMeeting) obj;
        return agenda.equals(other.agenda) && duration == other.duration;
    }
}

class Conference extends Event {
    private String topic;
    private int maxParticipants;

    public Conference(LocalDate date, String topic, int maxParticipants) {
        super("Конференция", date);
        this.topic = topic;
        this.maxParticipants = maxParticipants;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getMaxParticipants() {
        return maxParticipants;
    }

    public void setMaxParticipants(int maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

    @Override
    public String toString() {
        return String.format("%s, Тема: %s, Макс. участников: %d",
                super.toString(), topic, maxParticipants);
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;
        Conference other = (Conference) obj;
        return topic.equals(other.topic) && maxParticipants == other.maxParticipants;
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("=== РАБОТА С ARRAYLIST ===\n");

        ArrayList<Event> events = new ArrayList<>();
        events.add(new Event("Совещание", LocalDate.of(2024, 12, 10)));
        events.add(new BusinessMeeting(LocalDate.of(2024, 11, 15), "Планирование", 120));
        events.add(new Conference(LocalDate.of(2024, 12, 5), "Технологии", 100));
        events.add(new Event("Встреча", LocalDate.of(2024, 11, 20)));
        events.add(new BusinessMeeting(LocalDate.of(2024, 12, 1), "Отчетность", 90));

        System.out.println("2. ВЫВОД СПИСКА:");
        System.out.println("Через for:");
        for (int i = 0; i < events.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + events.get(i));
        }

        System.out.println("\nЧерез for-each:");
        int counter = 1;
        for (Event event : events) {
            System.out.println("  " + counter + ". " + event);
            counter++;
        }

        System.out.println("\n3. ДОБАВЛЕНИЕ ОБЪЕКТОВ:");
        events.add(0, new Conference(LocalDate.of(2024, 11, 25), "Инновации", 50)); // в начало
        events.add(new Event("Закрытое мероприятие", LocalDate.of(2024, 12, 20))); // в конец

        System.out.println("Список после добавления:");
        for (int i = 0; i < events.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + events.get(i));
        }

        System.out.println("\n4. УДАЛЕНИЕ ЭЛЕМЕНТОВ:");
        Event elementToRemove = events.get(2);
        events.remove(1);
        System.out.println("Удален элемент по индексу 1");
        events.remove(elementToRemove);
        System.out.println("Удален элемент по значению: " + elementToRemove.getType());
        System.out.println("Список после удаления:");
        for (int i = 0; i < events.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + events.get(i));
        }

        System.out.println("\n5. ТЕКУЩИЙ РАЗМЕР СПИСКА:");
        System.out.println("Размер списка: " + events.size() + " элементов");

        System.out.println("\n6. ПОЛУЧЕНИЕ ЭЛЕМЕНТА ПО ИНДЕКСУ:");
        if (!events.isEmpty()) {
            Event selectedEvent = events.get(0);
            System.out.println("Первый элемент списка: " + selectedEvent.getType());
            System.out.println("Дата события: " + selectedEvent.getDate());
        }

        System.out.println("\n7. ИЗМЕНЕНИЕ ПОЛЯ ОБЪЕКТА:");
        if (!events.isEmpty()) {
            Event eventToModify = events.get(0);
            System.out.println("До изменения: " + eventToModify);
            eventToModify.setResult("Успешно завершено");
            System.out.println("После изменения: " + eventToModify);
        }

        System.out.println("\n8. ПОИСК ОБЪЕКТОВ ПО УСЛОВИЮ:");
        System.out.println("Поиск встреч с длительностью > 100 минут:");
        for (Event event : events) {
            if (event instanceof BusinessMeeting) {
                BusinessMeeting meeting = (BusinessMeeting) event;
                if (meeting.getDuration() > 100) {
                    System.out.println("Найдена встреча: " + meeting);
                    break;
                }
            }
        }

        System.out.println("\nПоиск конференций с макс. участников < 80:");
        for (Event event : events) {
            if (event instanceof Conference) {
                Conference conference = (Conference) event;
                if (conference.getMaxParticipants() < 80) {
                    System.out.println("Найдена конференция: " + conference);
                    break;
                }
            }
        }

        System.out.println("\n9. ПРОВЕРКА НАЛИЧИЯ ОБЪЕКТА:");
        Event testEvent = new Event("Тестовое событие", LocalDate.of(2024, 12, 25));
        events.add(testEvent);
        System.out.println("Содержится ли testEvent в списке: " + events.contains(testEvent));
        Event sameEvent = new Event("Тестовое событие", LocalDate.of(2024, 12, 25));
        System.out.println("Содержится ли sameEvent в списке: " + events.contains(sameEvent));
        System.out.println("\n10. ОЧИСТКА СПИСКА:");
        System.out.println("Размер до очистки: " + events.size());
        events.clear();
        System.out.println("Размер после очистки: " + events.size());
        System.out.println("Список пуст: " + events.isEmpty());

        System.out.println("\n=== ВЫПОЛНЕНИЕ ЗАВЕРШЕНО ===");
    }
}