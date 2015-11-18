

Решение задачи Почтовая служба.

// Непроверяемые исключения – наследники RuntimeException
public static class StolenPackageException extends RuntimeException {
}


public static class IllegalPackageException extends RuntimeException {
}


public static class UntrustworthyMailWorker implements MailService {
    // Внутренний экземпляр RealMailService можно объявит прямо в поле,
    // или же, например, в конструкторе.
    private static final MailService realService = new RealMailService();
    private final MailService[] agents;

    public UntrustworthyMailWorker(final MailService[] agents) {
        this.agents = agents;
    }

    public MailService getRealMailService() {
        return realService;
    }

    @Override
    public Sendable processMail(Sendable mail) {
        for (MailService agent : agents) {
            mail = agent.processMail(mail);
        }
        return realService.processMail(mail);
    }
}


public static class Spy implements MailService {
    private final Logger logger;

    public Spy(final Logger logger) {
        this.logger = logger;
    }

    @Override
    public Sendable processMail(Sendable mail) {
        if (mail instanceof MailMessage) {
            String direction = "from " + mail.getFrom() + " to " + mail.getTo();
            if (isTargetMail(mail)) {
                // Здесь так же неплохо выглядел бы вызов логгера с объектными параметрами.
                logger.warning(
                        "Detected target mail correspondence: "
                        + direction + " \"" + ((MailMessage) mail).getMessage() + "\"");
            } else {
                logger.info("Usual correspondence: " + direction);
            }
        }
        return mail;
    }

    private boolean isTargetMail(Sendable mail) {
        // Сравнивать объекты на равенство лучше всего через метод объекта,
        // который не может равнятся null.
        // Это помогает избегать неожиданных NullPointerException.
        // Если оба объекта могут быть null, может помочь Objects.equals
        return AUSTIN_POWERS.equals(mail.getFrom()) || AUSTIN_POWERS.equals(mail.getTo());
    }
}


public static class Inspector implements MailService {

    private static final String[] ILLEGAL_CONTENT =
            new String[]{WEAPONS, BANNED_SUBSTANCE};

    @Override
    public Sendable processMail(Sendable mail) {
        if (mail instanceof MailPackage) {
            MailPackage mailPackage = (MailPackage) mail;
            if (mailPackage.getContent().getContent().contains("stones")) {
                throw new StolenPackageException();
            }
            for (String illegalString : ILLEGAL_CONTENT) {
                if (mailPackage.getContent().getContent().contains(illegalString)) {
                    throw new IllegalPackageException();
                }
            }
        }
        return mail;
    }
}


public static class Thief implements MailService {
    private final int minValueToSteal;
    private int stolenValue = 0;

    public Thief(int minValueToSteal) {
        this.minValueToSteal = minValueToSteal;
    }

    public int getStolenValue() {
        return stolenValue;
    }

    @Override
    public Sendable processMail(Sendable mail) {
        if (mail instanceof MailPackage) {
            Package content = ((MailPackage) mail).getContent();
            if (content.getPrice() >= minValueToSteal) {
                stolenValue += content.getPrice();
                return new MailPackage(
                        mail.getFrom(), mail.getTo(), stolenPackage(content));
            } else
                return mail;
        } else {
            return mail;
        }
    }

    private Package stolenPackage(Package content) {
        return new Package("stones instead of " + content.getContent(), 0);
    }
}

